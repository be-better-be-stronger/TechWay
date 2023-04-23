package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.service.IOrderDetailService;
import com.techway.service.IOrderService;
import com.techway.service.IUserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	IOrderService orderService;
	@Autowired
	IUserService userservice;
	@Autowired
	IOrderDetailService orderDetailService;
	
//	@GetMapping
//	public ResponseEntity<OrderResponse> getAll()
}
