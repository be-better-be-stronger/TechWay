package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.ScreenTech;

public interface ScreenTechRepository extends JpaRepository<ScreenTech, Long> {
	List<ScreenTech> findByNameContaining(String name);
	List<ScreenTech> findScreenTechsByLaptopsId(Long productId);
}
