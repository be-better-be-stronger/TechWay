package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.LaptopDetail;

public interface LaptopDetailRepository extends JpaRepository<LaptopDetail, Long>{
	List<LaptopDetail> findLaptopsByScreenTechsId(Long screenTechId);
}
