package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.model.entity.PhoneDetail;
import com.techway.repository.PhoneDetailRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/phonedetails")
public class PhoneDetailController {
	@Autowired
	PhoneDetailRepository phoneDetailRepository;
	
	
	@PostMapping
	public PhoneDetail create(@RequestBody PhoneDetail o) {
		return phoneDetailRepository.save(o);
	}
}