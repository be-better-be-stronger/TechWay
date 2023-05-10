package com.techway.service;

import java.util.List;

import com.techway.dto.OrderDto;
import com.techway.dto.request.OrderRequest;

public interface OrderService {
	List<OrderDto> getAllOrdersByEmail(String email);

	OrderDto getOrderById(String id, String email);

	OrderDto createOrder(OrderRequest orderRequest, String email);

	boolean deleteOrder(String id, String email);

}
