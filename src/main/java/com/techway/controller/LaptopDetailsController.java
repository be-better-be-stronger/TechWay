package com.techway.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.techway.model.dto.request.LaptopDetailsRequest;
import com.techway.model.entity.LaptopDetail;
import com.techway.service.ILaptopDetailsService;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/v1/laptopdetails")
public class LaptopDetailsController {
	@Autowired
	ILaptopDetailsService laptopDetailsService;

	@GetMapping
	public ResponseEntity<List<LaptopDetail>> getAll() {
		List<LaptopDetail> list = new ArrayList<>();
		laptopDetailsService.findAll().forEach(list::add);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<LaptopDetail> getOne(@PathVariable("id") long id) {
		return new ResponseEntity<>(laptopDetailsService.findById(id), HttpStatus.OK);
	}

	@PostMapping("{productId}")
	public ResponseEntity<LaptopDetail> create(@PathVariable("productId") long id,
			@RequestBody LaptopDetailsRequest request) {
		return new ResponseEntity<LaptopDetail>(laptopDetailsService.save(id, request), HttpStatus.CREATED);
	}
	@PutMapping("{productId}")
	public ResponseEntity<LaptopDetail> update(@PathVariable("productId") long id,
			@RequestBody LaptopDetailsRequest request) {
		return new ResponseEntity<LaptopDetail>(laptopDetailsService.update(id, request), HttpStatus.CREATED);
	}
}
