package com.techway.model.entity;

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
@Table(name = "screen_techs_laptop_details")
public class ScreenTech_LaptopDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(name = "laptop_detail_id")
	LaptopDetail laptopDetail;
	
	@ManyToOne
	@JoinColumn(name = "screen_tech_id")
	ScreenTech screenTech;
	
}
