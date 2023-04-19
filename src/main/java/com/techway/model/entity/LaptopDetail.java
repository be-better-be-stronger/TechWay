package com.techway.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import lombok.Data;

@Data
@Entity
@Table(name = "laptop_details")
public class LaptopDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private Long id;

	// @MapsId -> indicates that the primary key values
	// will be copied from the Product entity
	@OneToOne
	@MapsId
	@JoinColumn(name = "product_id")
	private Product product;

	// Bộ xử lý
	@Nationalized
	private String cpu; // cong nghe CPU
	private int core; // số nhân
	private int thread; // số luồng
	private float cpuSpeed; // Tốc độ CPU(GHz)
	private float cpuMaxSpeed; // Tốc độ tối đa(Turbo Boost<GHz>)
	private int cache; // Bộ nhớ đệm(MB)

	// Bộ nhớ RAM, Ổ cứng
	private int ram; // RAM
	@Nationalized
	private String type; // loại RAM
	private int busRAM; // Tốc độ Bus RAM
	private int maxRAM; // Hỗ trợ RAM tối đa
	@Nationalized
	private String ssd;

	// Màn hình
	float screenWidth; // Màn hình(inch)
	@Nationalized
	String screenResolution; // do phan giai man hinh
	int hz; // tần số quét màn hình

	// cong nghệ màn hình
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "laptop_screen_technologies", joinColumns = { @JoinColumn(name = "laptop_id") }, inverseJoinColumns = {
			@JoinColumn(name = "screen_tech_id") })
	private Set<ScreenTech> screenTechs = new HashSet<>();

	// Đồ họa và Âm thanh
	@Nationalized
	String screenCard; // card màn hình
	@Nationalized
	String sound; // Công nghệ âm thanh

	public void addScreenTech(ScreenTech technology) {
		this.screenTechs.add(technology);
		technology.getLaptops().add(this);
	}

	public void removeScreenTech(ScreenTech technology) {		
			this.screenTechs.remove(technology);
			technology.getLaptops().remove(this);
	}

}
