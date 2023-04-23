package com.techway.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private double deliveryFee;
	private List<Long> orderDetais;
	private String address;
	private 
	String phone;
}
