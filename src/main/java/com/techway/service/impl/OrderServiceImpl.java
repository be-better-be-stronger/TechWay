package com.techway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.repository.OrderRepository;
import com.techway.service.OrderService;
import com.techway.service.UserService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserService userService;

}
