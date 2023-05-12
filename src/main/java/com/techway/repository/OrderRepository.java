package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
	@Query("select o from Order o where o.user.email=?1")
	List<Order> findByEmail(String email);

	@Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.user.email = :email ORDER BY o.orderDate DESC")
	Order findByOrderIdAndUserEmailSortedByRecentOrderDate(@Param("orderId") String orderId, @Param("email") String email);

	
	@Query("SELECT o FROM Order o WHERE o.id = :orderId AND o.user.email = :email")
    Order findByOrderIdAndUserEmail(String orderId, String email);
}
