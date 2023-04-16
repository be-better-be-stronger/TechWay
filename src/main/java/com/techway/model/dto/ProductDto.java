package com.techway.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	private long id;
	private String productNo;
	private String name;
	private String image;
	private double price;
	private Boolean available;
	private Date createdDate = new Date();
	private int categoryId;
	private int manufacturerId;
	private int colorId;
	
	
}
