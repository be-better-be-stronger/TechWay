package com.techway.controller;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.createAnnotation.CurrentUser;
import com.techway.dto.AccountDto;
import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;
import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Product;
import com.techway.entity.User;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CartRepository;
import com.techway.repository.UserRepository;
import com.techway.security.UserDetailsImpl;
import com.techway.service.CartService;
import com.techway.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    
    @Autowired
    private UserService userService;

    // Lấy thông tin giỏ hàng theo User
    @GetMapping
    public ResponseEntity<CartDto> getCartByUser(@CurrentUser UserDetailsImpl loginUser) {
    	long userId = loginUser.getId();
        CartDto cart = cartService.getCartByUserId(userId);        
        return ResponseEntity.ok().body(cart);
    }

    // Thêm sản phẩm vào giỏ hàng
    @PostMapping("/product/{productId}")
    public ResponseEntity<Boolean> addProductToCart(@PathVariable("productId") Long productId,@CurrentUser UserDetailsImpl loginUser) {
        Long userId = loginUser.getId();
        Boolean plusStatus = cartService.addProductToCart(userId, productId);        
        return ResponseEntity.ok().body(plusStatus);
    }

    // Cập nhật số lượng sản phẩm trong giỏ hàng
    @PutMapping("/{userId}/updateAmount")
    public ResponseEntity<CartDto> updateProductInCart(@CurrentUser UserDetailsImpl loginUser, @RequestBody CartItemDto cartItemDto) {
    	 
    	Long userId = loginUser.getId();
    	CartDto cartDTO = cartService.updateProductInCart(userId, cartItemDto);

         if (cartDTO == null) {
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok().body(cartDTO);
    }

    // Xóa sản phẩm trong giỏ hàng
    @DeleteMapping("/{userId}/removeProduct/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@CurrentUser UserDetailsImpl loginUser, @PathVariable Long productId) {
        
    	Long userId = loginUser.getId();
    	cartService.deleteCartItem(userId, productId);
        return ResponseEntity.noContent().build();
    }
    

    // Tính tổng giá trị của các sản phẩm trong giỏ hàng
    private Double calculateTotalValue(List<CartItem> cartItems) {
        Double totalValue = 0.0;
        
        for (CartItem item : cartItems) {
            totalValue += item.getProduct().getPrice() * item.getQuantity();
        }
        
        return totalValue;
    }
}
