package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techway.model.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
	@Query("select o from Order o where o.user.email=?1")
	List<Order> findByEmail(String email);

}
