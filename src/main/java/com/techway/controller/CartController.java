package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;
import com.techway.service.CartService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    // Lấy thông tin giỏ hàng theo User
    @GetMapping
    public ResponseEntity<CartDto> getCartByUser(Authentication authentication) {
    	String user = authentication.getName();// return email
    	System.out.println(user);
        CartDto cart = cartService.getCartByUserEmail(user);        
        return ResponseEntity.ok().body(cart);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/product/{productId}")
    public ResponseEntity<Boolean> addProductToCart(@PathVariable("productId") Long productId, Authentication authentication) {
    	String email = authentication.getName();// return email
    	System.out.println(email);
        Boolean plusStatus = cartService.addProductToCart(email, productId);     
        System.out.println(plusStatus);
        return ResponseEntity.ok().body(plusStatus);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}/updateAmount")
    public ResponseEntity<CartDto> updateProductInCart(@RequestBody CartItemDto cartItemDto, Authentication authentication) {
    	 
    	String email = authentication.getName();
    	CartDto cartDTO = cartService.updateProductInCart(email, cartItemDto);

         if (cartDTO == null) {
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(cartDTO);
    }

    // Xóa sản phẩm trong giỏ hàng
    @DeleteMapping("/remove-product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(Authentication authentication, @PathVariable Long productId) {
        String email = authentication.getName();
        System.out.println(email);
    	cartService.deleteCartItem(email, productId);
        return ResponseEntity.noContent().build();
    }
    
    //Xóa toàn bộ sản phẩm trong giỏ hàng
    @DeleteMapping("/clear-cart")
    public ResponseEntity<Void> clearCart(Authentication authentication) {
        String email = authentication.getName();
        System.out.println(email);
    	cartService.clearCart(email);
        return ResponseEntity.noContent().build();
    }
    

    // Tính tổng giá trị của các sản phẩm trong giỏ hàng
//    private Double calculateTotalValue(List<CartItem> cartItems) {
//        Double totalValue = 0.0;
//        
//        for (CartItem item : cartItems) {
//            totalValue += item.getProduct().getPrice() * item.getQuantity();
//        }
//        
//        return totalValue;
//    }
}
