package com.techway.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;
import com.techway.dto.ProductDto;
import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Product;
import com.techway.entity.User;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CartItemRepository;
import com.techway.repository.CartRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.UserRepository;
import com.techway.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public CartDto getCartByUserId(Long userId) {
		User user = userRepository.findById(userId).orElse(null);
		if (user == null) {
			return null;
		}
		Cart cart = cartRepository.findByUser(user);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cart = cartRepository.save(cart);
		}
		CartDto cartDto = CartDto.fromEntity(cart);
		return cartDto;
	}

	@Override
	public Boolean addProductToCart(Long userId,Long productId) {
		User user = userRepository.findById(userId).orElse(null);
		Product product = productRepository.findById(productId).orElse(null);
		if (user == null || product == null) {
			return false;
		}
		Cart cart = cartRepository.findByUser(user);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);			
			cart = cartRepository.save(cart);
			CartItem cartItem = new CartItem();
			cartItem.setQuantity(1);
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItemRepository.save(cartItem);
		}else {
			CartItem cartItem = cartItemRepository.findByProductAndCart(product, cart);
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			cartItemRepository.save(cartItem);
		}
		return true;
	}

	@Override
	public void deleteCartItem(Long userId, Long productId) {
		Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            return;
        }

        User user = userOptional.get();
        Cart cart = cartRepository.findByUser(user);

        if (cart == null) {
            return;
        }

        Optional<CartItem> cartItemOptional = cart.getCartItems().stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst();

        if (!cartItemOptional.isPresent()) {
            return;
        }

        CartItem cartItem = cartItemOptional.get();
        cart.getCartItems().remove(cartItem);

        Double totalValue = calculateTotalValue(cart);
        cart.setTotalValue(totalValue);

        cartRepository.save(cart);
	}

	

	@Override
	public void clearCart(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
				() -> new ResourceNotFoundException(String.format("User with id %d not found", userId))
				);
		cartRepository.deleteByUser(user);		
	}

	@Override
	public CartDto updateProductInCart(Long userId, CartItemDto cartItemDto) {
	       User user = userRepository.findById(userId).get();

	        Cart cart = cartRepository.findByUser(user);

	        if (cart == null) {
	            return null;
	        }

	        Optional<CartItem> cartItems = cart.getCartItems().stream()
	                .filter(ci -> ci.getProduct().getId().equals(cartItemDto.getProduct().getId()))
	                .findFirst();

	        if (!cartItems.isPresent()) {
	            return null;
	        }

	        CartItem cartItem = cartItems.get();
	        cartItem.setQuantity(cartItemDto.getQuantity());

	        Double totalValue = calculateTotalValue(cart);
	        cart.setTotalValue(totalValue);

	        Cart savedCart = cartRepository.save(cart);
	        CartDto savedCartDTO = CartDto.fromEntity(savedCart);

	        return savedCartDTO;
	}

	private Double calculateTotalValue(Cart cart) {
		Double totalValue = 0.0;

        for (CartItem cartItem : cart.getCartItems()) {
            Double itemValue = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            totalValue += itemValue;
        }

        return totalValue;
	}

	
}
