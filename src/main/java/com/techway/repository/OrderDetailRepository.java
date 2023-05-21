package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techway.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	OrderDetail findByOrderIdAndProductId(String orderId, Long productId);

	boolean existsByOrder_IdAndProduct_Id(String orderId, Long productId);
//	tổng doanh thu
	@Query("SELECT SUM(od.quantity * od.product.price) FROM OrderDetail od")
	Double getTotalRevenue();
	
}
