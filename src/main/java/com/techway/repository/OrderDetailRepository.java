package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{

}
