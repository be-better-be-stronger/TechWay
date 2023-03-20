package com.techway.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	String content;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
	@Temporal(TemporalType.DATE)
	private Date createdDate = new Date();
	
	
}
