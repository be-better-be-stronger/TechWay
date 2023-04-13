package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.Manufacturer;

public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer>{

}
