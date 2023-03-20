package com.techway.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "phone_detail_has_gps")
public class GPS_PhoneDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne()
	@JoinColumn(name = "gps_id")
	GPS gps;
	
	@ManyToOne()
	@JoinColumn(name = "phone_detail_id")
	PhoneDetail phoneDetail;
}
