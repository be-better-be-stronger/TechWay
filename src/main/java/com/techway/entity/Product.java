package com.techway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(mappedBy = "product")
	@PrimaryKeyJoinColumn
    private PhoneDetail phoneDetail;
	
	@OneToOne(mappedBy = "product")
	@PrimaryKeyJoinColumn
    private LaptopDetail laptopDetail;

	@Column(name = "name")
	private String name;	
	private String image;	
	private Double price;
	private boolean available;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate = new Date();	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "manufacturer_id")
	private Manufacturer manufacturer;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_id")
	private Color color;
	
	@JsonIgnore //when we use json(rest api) will ignore this field to avoid making mistake
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product")
	private List<Comment> comments;
		
}
