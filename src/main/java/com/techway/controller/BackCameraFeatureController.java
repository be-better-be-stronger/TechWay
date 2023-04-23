package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.CameraFeature;
import com.techway.repository.CameraFeatureRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/bcf")
public class BackCameraFeatureController {
	
	@Autowired
	CameraFeatureRepository bcfRepo;
	@GetMapping
	public List<CameraFeature> list(){
		return bcfRepo.findAll();
	}

	@GetMapping("{id}")
	public CameraFeature getOne(@PathVariable("id") Integer id) {
		return bcfRepo.findById(id).get();
	}

	@PostMapping
	public CameraFeature create(@RequestBody CameraFeature o) {
		return bcfRepo.save(o);
	}

	@PutMapping("{id}")
	public CameraFeature update(@PathVariable("id") Integer id, @RequestBody CameraFeature o) {
		return bcfRepo.save(o);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		bcfRepo.deleteById(id);
	}
}
