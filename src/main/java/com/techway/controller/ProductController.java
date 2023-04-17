package com.techway.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techway.model.dto.request.ProductRequest;
import com.techway.model.dto.response.ProductResponse;
import com.techway.service.IProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
public class ProductController {

	@Autowired
	IProductService productService;
	
	@GetMapping("/name")
	public ResponseEntity<List<ProductResponse>> findByName(@RequestParam(required = false) String name){
		List<ProductResponse> products = new ArrayList<>();
		if(name == null)
			productService.findByNameContaining(name).forEach(products::add);
		else
			productService.findAll().forEach(products::add);		
		if (products.isEmpty())
		      return new ResponseEntity<>(HttpStatus.NO_CONTENT);		    
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<ProductResponse>> list(@RequestParam("cid") Optional<Integer> cid) {
		List<ProductResponse> products = new ArrayList<>();
		if (cid.isPresent())
			productService.findAllByCategoryId(cid.get()).forEach(products::add);
		else
			productService.findByAvailable().forEach(products::add);
		if (products.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> getOne(@PathVariable("id") long id) {
		return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(productService.save(productRequest), HttpStatus.CREATED);
	}

	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponse> update(@PathVariable("id") long id, @RequestBody ProductRequest productRequest) {
		return new ResponseEntity<>(productService.update(id, productRequest), HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id) {
		productService.disable(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}
}
