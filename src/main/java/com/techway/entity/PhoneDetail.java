package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "phone_details")
public class PhoneDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "product_id")
	private Integer id;
	
	//@MapsId -> indicates that the primary key values 
	//will be copied from the Product entity
	@OneToOne
	@MapsId
	@JoinColumn(name = "product_id")
	private Product product;
	
	/*
	 * nhóm các thông số: 1-màn hình, 2-camera sau, 3-camera trước, 4-HDH, 5-Bộ nhớ
	 * & Lưu trữ, 6-Kết nối, 7-pin & sạc, 8-tiện ích, 9-thông tin chung
	 */

	// Màn hình
	@ManyToOne()
	@JoinColumn(name="screen_tech_id")
	private ScreenTech screenTech; // cong nghe man hinh
	private String screenResolution; // độ phân giải
	private float screenWidth; // màn hình rộng
	private String maxLight; // độ sáng tối đa
	private String glass; // Mặt kính cảm ứng

	// Camera sau
	private String backCameraResolution;// Độ phân giải
	private boolean flash;// Đèn Flash

	@OneToMany(mappedBy = "phoneDetail")
	private List<BackCameraFeature_PhoneDetail> backCameraFeatures;// Tính năng

	// Camera trước
	private int frontCameraResolution;// Độ phân giải

	@OneToMany(mappedBy = "phoneDetail")
	private List<FrontCameraFeature_PhoneDetail> frontCameraFeatures;// Tính năng

	// Hệ điều hành & CPU
	private String os;// Hệ điều hành
	private String cpu;// Chip xử lý (CPU)
	private String cpuSpeed;// Tốc độ CPU
	private String gpu;// Chip đồ họa (GPU)

	// Bộ nhớ & Lưu trữ
	int ram; // RAM
	int rom; // Dung lượng lưu trữ
	float romUseable;// Dung lượng còn lại (khả dụng) khoảng
	String contacts;// Danh bạ

	// Kết nối
	String mobileNetwork; // Mạng di động
	String sim; // SIM
	String bluetooth;// Bluetooth
	String port;// Cổng kết nối/sạc
	String jackPhone;// Jack tai nghe

	// Pin & Sạc
	int pinCapacity;// Dung lượng pin
	String pinType;// Loại pin
	int chargingSupport;// Hỗ trợ sạc tối đa

	// Tiện ích
	@OneToMany(mappedBy = "phoneDetail")
	List<AdvancedSecurity_PhoneDetail> securities; // Bảo mật nâng cao

	@OneToMany(mappedBy = "phoneDetail")
	List<SpecialFeature_PhoneDetail> specialFeatures;// Tính năng đặc biệt




	// Thông tin chung
	String design; // Thiết kế
	String material;// Chất liệu
	String dimensionAndWeight;// Kích thước, khối lượng
}
