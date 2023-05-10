package com.techway.dto.request;

import com.techway.dto.CartDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private CartDto cart;
	private double deliveryFee;
	private String address;
	private String phone;
}
