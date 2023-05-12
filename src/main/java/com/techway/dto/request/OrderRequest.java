package com.techway.dto.request;

import java.util.List;

import com.techway.entity.CartItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
	private List<CartItem> cartItems;
	private double shipping; //phí ship
	private String address;
	private String phone;
}
