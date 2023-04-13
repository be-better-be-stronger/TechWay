package com.techway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.Color;

public interface ColorRepository extends JpaRepository<Color, Integer>{

}
