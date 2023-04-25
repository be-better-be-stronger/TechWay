package com.techway.service;

import com.techway.dto.CartDto;
import com.techway.dto.CartItemDto;

public interface CartService {
	
	public CartDto getCartByUserId(Long userId);

//    public CartDto addCartItem(Long userId, Long productId, Integer quantity);

    public void deleteCartItem(Long userId, Long productId);

    public void clearCart(Long userId);

	public Boolean addProductToCart(Long userId, Long  productId);

	public CartDto updateProductInCart(Long userId, CartItemDto cartItemDto);

}
