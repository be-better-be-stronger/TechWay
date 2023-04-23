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
import com.techway.service.IColorSerrvice;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/colors")
public class ColorController {
	
	@Autowired
	IColorSerrvice colorService;
	
	@GetMapping
	public List<Color> list(){
		return colorService.findAll();
	}

	@GetMapping("{id}")
	public Color getOne(@PathVariable("id") Integer id) {
		return colorService.findById(id);
	}

	@PostMapping
	public Color create(@RequestBody Color color) {
		return colorService.save(color);
	}

	@PutMapping("{id}")
	public Color update(@PathVariable("id") Integer id, @RequestBody Color color) {
		return colorService.update(id, color);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		colorService.deleteById(id);
	}
}
