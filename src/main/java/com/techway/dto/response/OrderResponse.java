package com.techway.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
	private String id;
	private String shippingStatus;
	private double subTotal;
	private double deliveryFee;
	private double total;
	private Date orderDate;
	private String address;
	private String phone;
}
