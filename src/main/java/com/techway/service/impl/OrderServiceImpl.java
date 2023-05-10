//package com.techway.service.impl;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.techway.ShippingStatus;
//import com.techway.dto.CartDto;
//import com.techway.dto.OrderDto;
//import com.techway.entity.Cart;
//import com.techway.entity.CartItem;
//import com.techway.entity.Order;
//import com.techway.entity.OrderDetail;
//import com.techway.repository.OrderRepository;
//import com.techway.service.OrderService;
//
//@Service
//public class OrderServiceImpl implements OrderService {
//
//	@Autowired
//	private OrderRepository orderRepository;
//
//	@Override
//	public List<OrderDto> getAllOrdersByEmail(String email) {
//		List<Order> orders = orderRepository.findByEmail(email);
//		return orders.stream()
//				.map(OrderDto::fromEntity)
//				.collect(Collectors.toList());
//	}
//
//	@Override
//	public OrderDto getOrderById(String id, String email) {
//		Optional<Order> optionalOrder = orderRepository.findById(id);
//	    if (optionalOrder.isPresent()) {
//	        Order order = optionalOrder.get();
//	        return OrderDto.fromEntity(order);
//	    }
//	    return null;
//	}
//
//	@Override
//	@Transactional
//	public OrderDto createOrder(OrderDto orderRequest, String email) {
//		// Chuyển đổi CartDto sang Cart
//		Cart cart = orderRequest.get
//
//		// Tính toán giá trị đơn hàng
//		double totalPrice = cart.getTotalValue();
//
//		// Lưu Cart vào database
//		Cart savedCart = cartRepository.save(cart);
//
//		// Chuyển đổi Cart sang Order
//		Order order = new Order();
//		order.setShippingStatus(String.valueOf(ShippingStatus.PREPARING));
//		order.setDeliveryFee(0.0);
//		order.setOrderDate(new Date());
//		order.setAddress(cart.geta);
//		order.setPhone(savedCart.getUser().getPhone());
//		order.setUser(savedCart.getUser());
//
//		List<OrderDetail> orderDetails = new ArrayList<>();
//		for (CartItem cartItem : savedCart.getCartItems()) {
//			OrderDetail orderDetail = new OrderDetail();
//			orderDetail.setQuantity(cartItem.getQuantity());
//			orderDetail.setProduct(cartItem.getProduct());
//			orderDetail.setOrder(order);
//			orderDetails.add(orderDetail);
//		}
//		order.setOrderDetails(orderDetails);
//
//		// Lưu Order vào database
//		Order savedOrder = orderRepository.save(order);
//
//		// Chuyển đổi Order sang OrderDto
//		OrderDto orderDto = modelMapper.map(savedOrder, OrderDto.class);
//		return orderDto;
//	}
//
//	
//
//	@Override
//	@Transactional
//	public boolean deleteOrder(String id, String email) {
//		Optional<Order> optionalOrder = orderRepository.findById(id);
//		if (optionalOrder.isPresent()) {
//			Order order = optionalOrder.get();
//			orderRepository.delete(order);
//			return true;
//		}
//		return false;
//	}
//
//
//
////	private double calculateTotalPrice(Cart cart) {
////		double totalPrice = 0.0;
////		for (CartItem cartItem : cart.getCartItems()) {
////			totalPrice += cartItem.getProduct().getPrice() * cartItem.getQuantity();
////		}
////		return totalPrice;
////	}
//
//}