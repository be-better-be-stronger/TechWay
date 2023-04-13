package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.CameraFeature;

public interface BackCameraFeatureRepository extends JpaRepository<CameraFeature, Integer>{

}
