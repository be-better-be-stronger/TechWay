package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.ScreenTech;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.LaptopDetailRepository;
import com.techway.repository.ScreenTechRepository;
import com.techway.service.ScreenTechService;

@Service
public class ScreenTechSerrviceImpl implements ScreenTechService {
	@Autowired
	ScreenTechRepository screenTechRepository;
	@Autowired
	LaptopDetailRepository laptopDetailRepository;

 	@Override
	public List<ScreenTech> findByNameContaining(String name) {
		return screenTechRepository.findByNameContaining(name);
	}

	@Override
	public List<ScreenTech> findAll() {
		return screenTechRepository.findAll();
	}

	@Override
	public ScreenTech findById(long id) {
		return screenTechRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("ScreenTech with id %d not found", id))
				);
	}

	@Override
	public ScreenTech save(ScreenTech o) {
		return screenTechRepository.save(o);
	}

	@Override
	public ScreenTech update(long id, ScreenTech o) {
		ScreenTech savedScreenTech = this.findById(id);
		savedScreenTech.setName(o.getName());
		return screenTechRepository.save(savedScreenTech);
	}

	@Override
	public boolean deleteById(long id) {
		ScreenTech o = this.findById(id);
		screenTechRepository.deleteById(o.getId());
		return true;
	}
	
	

}