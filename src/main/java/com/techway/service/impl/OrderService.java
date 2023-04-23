package com.techway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.repository.OrderRepository;
import com.techway.service.IOrderService;
import com.techway.service.IUserService;

@Service
public class OrderService implements IOrderService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	IUserService userService;

}
