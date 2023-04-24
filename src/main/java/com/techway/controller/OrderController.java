package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.service.OrderDetailService;
import com.techway.service.OrderService;
import com.techway.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/orders")
public class OrderController {
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	@Autowired
	OrderDetailService orderDetailService;
	
//	@GetMapping()
//	public ResponseEntity<List<OrderResponse>> getAllOrders(@CurrentUser UserDetailsImpl currentUser) {
//	    Long userId = currentUser.getId();
//	    User user = userService.findById(userId);
//	    List<OrderResponse> orders = orderService.findAllByUserId(user.getId());
//	    return ResponseEntity.ok(orders);
//	}
}
