package com.techway.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "laptop_details")
public class LaptopDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "product_id")
	private Long id;
	
	//@MapsId -> indicates that the primary key values 
	//will be copied from the Product entity
	@OneToOne
	@MapsId
	@JoinColumn(name = "product_id")
	private Product product;
	
	// Bộ xử lý
	private String cpu; // cong nghe CPU
	private int core; //số nhân
	private int thread; //số luồng
	private float cpuSpeed; // Tốc độ CPU(GHz)
	private float cpuMaxSpeed;  //Tốc độ tối đa(Turbo Boost<GHz>)
	private int cache; //Bộ nhớ đệm(MB)


	//Bộ nhớ RAM, Ổ cứng
	private int ram; //RAM
	private String type; //loại RAM
	private int busRAM; //Tốc độ Bus RAM
	private int maxRAM; //Hỗ trợ RAM tối đa
	private String ssd;
	
	//	Màn hình
	float screenWidth; //Màn hình(inch)
	String screenResolution; //do phan giai man hinh
	int hz; //tần số quét màn hình
	
	@JsonIgnore
	@OneToMany(mappedBy = "laptopDetail")
	List<ScreenTech_LaptopDetail> screenTechLaptopDetails; // cong nghệ màn hình
	
	// Đồ họa và Âm thanh	
	String screenCard; // card màn hình
	String sound; // Công nghệ âm thanh
	
	

}
