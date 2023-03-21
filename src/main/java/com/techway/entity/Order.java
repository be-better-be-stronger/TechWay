package com.techway.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable{
	@Id
	String id;
	double total;
	int status;
	@Temporal(TemporalType.DATE)
	@Column(name = "created_date")
	Date createdDate = new Date();
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
	
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
	
	String address;
	String phone;
}
