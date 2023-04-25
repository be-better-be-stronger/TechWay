package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	@Query("SELECT ci FROM CartItem ci WHERE ci.product = :product AND ci.cart = :cart")
    CartItem findByProductAndCart(@Param("product") Product product, @Param("cart") Cart cart);
}
