package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "phone_details")
public class PhoneDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	private String name;

	@OneToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id")
	private Product product;
	/*
	 * nhóm các thông số: 1-màn hình, 2-camera sau, 3-camera trước, 4-HDH, 5-Bộ nhớ
	 * & Lưu trữ, 6-Kết nối, 7-pin & sạc, 8-tiện ích, 9-thông tin chung
	 */

	// Màn hình
	private String screenTech; // cong nghe man hinh
	private String screenResolution; // độ phân giải
	private String screenWidth; // màn hình rộng
	private String maxLight; // độ sáng tối đa
	private String glass; // Mặt kính cảm ứng

	// Camera sau
	private String backCameraResolution;// Độ phân giải

	@OneToMany(mappedBy = "phoneDetail")
	private List<Film_PhoneDetail> films;// Quay phim

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

	@OneToMany(mappedBy = "phoneDetail")
	List<Wifi_PhoneDetail> wifiList;// Wifi

	@OneToMany(mappedBy = "phoneDetail")
	List<GPS_PhoneDetail> gpsList;// GPS
	String bluetooth;// Bluetooth
	String port;// Cổng kết nối/sạc
	String jackPhone;// Jack tai nghe

	// Pin & Sạc
	int pinCapacity;// Dung lượng pin
	String pinType;// Loại pin
	int chargingSupport;// Hỗ trợ sạc tối đa

	@OneToMany(mappedBy = "phoneDetail")
	List<PinTech_PhoneDetail> pinTechs;// Công nghệ pin

	// Tiện ích
	@OneToMany(mappedBy = "phoneDetail")
	List<AdvancedSecurity_PhoneDetail> securities; // Bảo mật nâng cao

	@OneToMany(mappedBy = "phoneDetail")
	List<SpecialFeature_PhoneDetail> specialFeatures;// Tính năng đặc biệt

	String ipIndex; // Kháng nước, bụi
	boolean recording;// Ghi âm
	@JsonIgnore
	@OneToMany(mappedBy = "phoneDetail")
	List<VideoPlayer_PhoneDetail> videoPlayers;// Xem phim

	@OneToMany(mappedBy = "phoneDetail")
	List<MusicPlayer_PhoneDetail> musicPlayers;// Nghe nhạc

	// Thông tin chung
	String design; // Thiết kế
	String material;// Chất liệu
	String dimensionAndWeight;// Kích thước, khối lượng
}
