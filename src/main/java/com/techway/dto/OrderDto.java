package com.techway.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techway.entity.Order;
import com.techway.entity.OrderDetail;
import com.techway.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class OrderDto {
	private String id;
	private String shippingStatus;
	private double deliveryFee;
	private Date orderDate;
	private List<OrderDetailDto> orderDetails = new ArrayList<>();
	private AccountDto user;
	private String address;
	private String phone;

	public static OrderDto fromEntity(Order order) {
		OrderDto dto = new OrderDto();
		dto.setId(order.getId());
		dto.setShippingStatus(order.getShippingStatus());
		dto.setDeliveryFee(order.getDeliveryFee());
		dto.setOrderDate(order.getOrderDate());
		dto.setAddress(order.getAddress());
		dto.setPhone(order.getPhone());

		// Chuyển đổi danh sách OrderDetail thành danh sách OrderDetailDto
		List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
		order.getOrderDetails().forEach(orderDetail -> {
			OrderDetailDto orderDetailDto = OrderDetailDto.fromEntity(orderDetail);
			orderDetailDtos.add(orderDetailDto);
		});
		dto.setOrderDetails(orderDetailDtos);

		// Chuyển đổi User thành UserDto
		AccountDto userDto = AccountDto.fromEntity(order.getUser());
		dto.setUser(userDto);

		return dto;
	}

	public Order toEntity() {
		Order order = new Order();
		order.setId(this.getId());
		order.setShippingStatus(this.getShippingStatus());
		order.setDeliveryFee(this.getDeliveryFee());
		order.setOrderDate(this.getOrderDate());
		order.setAddress(this.getAddress());
		order.setPhone(this.getPhone());

		// Chuyển đổi danh sách OrderDetailDto thành danh sách OrderDetail
		List<OrderDetail> orderDetails = new ArrayList<>();
		this.getOrderDetails().forEach(orderDetailDto -> {
			OrderDetail orderDetail = orderDetailDto.toEntity();
			orderDetails.add(orderDetail);
		});
		order.setOrderDetails(orderDetails);

		// Chuyển đổi UserDto thành User
		User user = this.getUser().toEntity();
		order.setUser(user);

		return order;
	}
	
	
}
