package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.SpecialFeature;

public interface SpecialFeatureRepository extends JpaRepository<SpecialFeature, Integer> {

}
