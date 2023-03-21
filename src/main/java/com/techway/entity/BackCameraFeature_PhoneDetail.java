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

@Data
@Entity
@Table(name = "phone_details_has_back_camera_features")
public class BackCameraFeature_PhoneDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne()
	@JoinColumn(name = "bcamera_feature_id")
	BackCameraFeature bCameraFeature;
	
	@ManyToOne()
	@JoinColumn(name = "phone_details_id")
	PhoneDetail phoneDetail;
}
