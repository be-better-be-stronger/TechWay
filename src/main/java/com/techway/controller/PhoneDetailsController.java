package com.techway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.service.IPhoneDetailService;
import com.techway.service.IProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/phone-details")
public class PhoneDetailsController {
	@Autowired
	IPhoneDetailService phoneDetailService;
	@Autowired
	IProductService productService;
	
	
	
	
}
