package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.entity.ScreenTech;
import com.techway.repository.LaptopDetailRepository;
import com.techway.repository.ScreenTechRepository;
import com.techway.service.IScreenTechService;

@Service
public class ScreenTechSerrvice implements IScreenTechService {
	@Autowired
	ScreenTechRepository screenTechRepository;
	@Autowired
	LaptopDetailRepository laptopDetailRepository;

 	@Override
	public List<ScreenTech> findByNameContaining(String name) {
		return screenTechRepository.findByNameContaining(name);
	}

	@Override
	public Iterable<ScreenTech> findAll() {
		return screenTechRepository.findAll();
	}

	@Override
	public ScreenTech findById(long id) {
		return screenTechRepository.findById(id).get();
	}

	@Override
	public ScreenTech save(ScreenTech o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ScreenTech update(long id, ScreenTech o) {
		ScreenTech savedScreenTech = this.findById(id);
		savedScreenTech.setName(o.getName());
		return screenTechRepository.save(savedScreenTech);
	}

	@Override
	public boolean deleteById(long id) {
		ScreenTech o = screenTechRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException
				(String.format("Screen technology whith id %d not found",id))
				);
		screenTechRepository.deleteById(o.getId());
		return true;
	}
	
	

}
