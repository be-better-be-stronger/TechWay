package com.techway.model.dto.response;

import java.util.Date;

import com.techway.model.entity.Category;
import com.techway.model.entity.Color;
import com.techway.model.entity.Manufacturer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	private long id;
	private String productNo;
	private String name;
	private String images;
	private double price;
	private boolean available;
	private Date createdDate;
	private Category category;
	private Manufacturer manufacturer;
	private Color color;
}
