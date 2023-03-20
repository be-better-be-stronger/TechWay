package com.techway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@OneToOne(mappedBy = "product")
    private PhoneDetail phonedetail;
	
	private String name;
	private String image;		
	private Double price;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date createdDate = new Date();
	
	private Boolean available;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@JsonIgnore //when we use json(rest api) will ignore this field to avoid making mistake
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "product")
	private List<Color_Product> color_product_list;
	
	@ManyToOne
	@JoinColumn(name = "manufaturer_id")
	private Manufacturer manufacturer;
	
}
