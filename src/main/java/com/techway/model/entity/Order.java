package com.techway.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Nationalized;

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
	@JoinColumn(name = "email")
	User user;
	@Nationalized
	String address;
	String phone;
}
