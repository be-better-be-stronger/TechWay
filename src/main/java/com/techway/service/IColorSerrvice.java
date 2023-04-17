package com.techway.service;

import java.util.List;

import com.techway.model.entity.Color;

public interface IColorSerrvice {

	List<Color> findAll();

	Color findById(Integer id);

	Color save(Color color);

	void deleteById(Integer id);

	Color update(Integer id, Color color);

}
