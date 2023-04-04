package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "camera_features")
public class CameraFeature implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cameraFeature")
	List<BackCameraFeature_PhoneDetail> bcfList;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cameraFeature")
	List<FrontCameraFeature_PhoneDetail> fcfList;
}