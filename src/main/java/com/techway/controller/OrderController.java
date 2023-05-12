//package com.techway.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import com.techway.dto.OrderDto;
//import com.techway.dto.request.OrderRequest;
//import com.techway.entity.Order;
//import com.techway.service.OrderDetailService;
//import com.techway.service.OrderService;
//import com.techway.service.UserService;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/v1/orders")
//public class OrderController {
//	
//	//1. lấy ra danh sách đơn hàng theo user(Role)
//	//2. 
//	
//	@Autowired
//	private OrderService orderService;
//
//	@GetMapping("/")
//	public ResponseEntity<List<OrderDto>> getAllOrders(Authentication authentication) {
//		String email = authentication.getName();
//		List<OrderDto> orders = orderService.getAllOrdersByEmail(email);
//		if (orders.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(orders, HttpStatus.OK);
//	}
//
//	@GetMapping("/{orderId}")
//	public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
//		Order order = orderService.getOrderById(orderId);
//		if (order == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}
//
//	@PostMapping("/")
//	public ResponseEntity<Order> createOrder(@RequestBody OrderRequest orderRequest) {
//		Order order = orderService.createOrder(orderRequest);
//		return new ResponseEntity<>(order, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/{orderId}")
//	public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody Order updatedOrder) {
//		Order order = orderService.updateOrder(orderId, updatedOrder);
//		if (order == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}
//
//	@DeleteMapping("/{orderId}")
//	public ResponseEntity<HttpStatus> deleteOrder(@PathVariable String orderId) {
//		boolean result = orderService.deleteOrder(orderId);
//		if (!result) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//	}
//}
