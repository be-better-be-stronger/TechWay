package com.techway.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techway.ShippingStatus;
import com.techway.dto.CartDto;
import com.techway.dto.OrderDetailDto;
import com.techway.dto.request.OrderRequest;
import com.techway.dto.response.OrderResponse;
import com.techway.entity.Cart;
import com.techway.entity.CartItem;
import com.techway.entity.Order;
import com.techway.entity.OrderDetail;
import com.techway.exception.APIException;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CartItemRepository;
import com.techway.repository.CartRepository;
import com.techway.repository.OrderRepository;
import com.techway.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartItemRepository cartItemRepository; 
	@Autowired
	private  CartRepository cartRepository;
	
	//1. lấy danh sách order theo user email
	//2. lấy order theo id, email order by recent place order
	//3. place  order

	@Override
	public List<OrderResponse> getAllOrdersByEmail(String email) {
		try {
			List<OrderResponse> orders = orderRepository.findByEmail(email).stream()
					.map(order -> fromEntity(order)).collect(Collectors.toList());
			return orders;
		} catch (ResourceNotFoundException e) {
			System.out.println(String.format("User with email %s has not been placed any order", email));
			return null;
		}
	}

	@Override
	public OrderResponse getOrderById(String id, String email) {
		try {
			Order savedOrder = orderRepository.findByOrderIdAndUserEmailSortedByRecentOrderDate(id, email);
			return fromEntity(savedOrder);
		} catch (APIException e) {
			System.out.println(String.format("Order with id %d and email %s not found", id, email));
			return null;
		}
	}

	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest, String email) {
		// TODO Auto-generated method stub
		return null;
	}	

	@Override
	@Transactional
	public boolean deleteOrder(String id, String email) {
		Optional<Order> optionalOrder = orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			Order order = optionalOrder.get();
			orderRepository.delete(order);
			return true;
		}
		return false;
	}

	public OrderResponse fromEntity(Order savedOrder) {
		OrderResponse response = new OrderResponse();
		response.setId(savedOrder.getId());
		response.setShippingStatus(savedOrder.getShippingStatus());
		response.setSubtotal(savedOrder.getSubtotal());
		response.setShipping(savedOrder.getShipping());
		response.setTotal(savedOrder.getShipping() + savedOrder.getSubtotal());
		response.setFullname(savedOrder.getUser().getFullname());
		response.setOrderDate(savedOrder.getOrderDate());
		response.setAddress(savedOrder.getAddress());
		response.setPhone(savedOrder.getPhone());

		List<OrderDetailDto> orderDetailDtos = savedOrder.getOrderDetails().stream().
				map(OrderDetailDto :: fromEntity).collect(Collectors.toList());
		response.setOrderDetails(orderDetailDtos);
		
		return response;
	}
	
	 public Order toEntity(OrderRequest request) {
		 
		 return null;
	 }
	 public String generateOrderId() {
		 String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	        int LENGTH = 10;
	        StringBuilder sb = new StringBuilder(LENGTH);

	        Random random = new Random();
	        for (int i = 0; i < LENGTH; i++) {
	            int randomIndex = random.nextInt(CHARACTERS.length());
	            char randomChar = CHARACTERS.charAt(randomIndex);
	            sb.append(randomChar);
	        }

	        String orderId = sb.toString();
	        while (orderRepository.existsById(orderId)) { // Kiểm tra xem ID đã tồn tại trong cơ sở dữ liệu chưa
	            sb.setLength(0); // Xóa chuỗi để tạo lại
	            for (int i = 0; i < LENGTH; i++) {
	                int randomIndex = random.nextInt(CHARACTERS.length());
	                char randomChar = CHARACTERS.charAt(randomIndex);
	                sb.append(randomChar);
	            }
	            orderId = sb.toString();
	        }
	        return orderId;
	 }

}