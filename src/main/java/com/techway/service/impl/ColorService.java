package com.techway.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.Color;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.ColorRepository;
import com.techway.service.IColorSerrvice;
@Service
public class ColorService implements IColorSerrvice {
	@Autowired
	ColorRepository colorRepository;
	@Override
	public List<Color> findAll() {
		List<Color> colors= new ArrayList<>();
		colorRepository.findAll().stream().forEach(
				colors::add
				);
		return colors;
	}

	@Override
	public Color findById(Integer id) {
		return colorRepository.findById(id).get();
	}

	@Override
	public Color save(Color color) {
		return colorRepository.save(color);
	}

	@Override
	public void deleteById(Integer id) {
		Color savedColor = colorRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException
				(String.format("Product with id %d not found", id))
				);
		colorRepository.delete(savedColor);		
	}

	@Override
	public Color update(Integer id, Color color) {
		Color savedColor = colorRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException
					(String.format("Product with id %d not found", id))
				);
		
		savedColor.setColor(color.getColor());
		return colorRepository.save(savedColor);
	}

}
