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

import com.techway.entity.Color;
import com.techway.repository.ColorRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/colors")
public class ColorController {
	
	@Autowired
	ColorRepository colorRepository;
	
	@GetMapping
	public List<Color> list(){
		return colorRepository.findAll();
	}

	@GetMapping("{id}")
	public Color getOne(@PathVariable("id") Integer id) {
		return colorRepository.findById(id).get();
	}

	@PostMapping
	public Color create(@RequestBody Color color) {
		return colorRepository.save(color);
	}

	@PutMapping("{id}")
	public Color update(@PathVariable("id") Integer id, @RequestBody Color color) {
		return colorRepository.save(color);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		colorRepository.deleteById(id);
	}
}
