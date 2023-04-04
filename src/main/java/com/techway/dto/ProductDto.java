package com.techway.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
	private long id;
	private String name;
	private String image;
	private double price;
	private boolean available;
	private int categoryId;
	private int manufacturerId;
	private int colorId;
}
