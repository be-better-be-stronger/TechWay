package com.techway.service;

import java.util.List;

import com.techway.entity.ScreenTech;

public interface ScreenTechService {

	List<ScreenTech> findByNameContaining(String name);

	List<ScreenTech> findAll();

	ScreenTech findById(long id);

	ScreenTech save(ScreenTech o);

	ScreenTech update(long id, ScreenTech o);

	boolean deleteById(long id);

}
