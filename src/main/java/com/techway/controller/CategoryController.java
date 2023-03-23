package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.entity.Category;
import com.techway.repository.CategoryRepository;

@CrossOrigin("*")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@GetMapping("categories")
	public List<Category> getAll(){
		return categoryRepository.findAll();
	}
}
