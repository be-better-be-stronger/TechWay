package com.techway.controller;

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

import com.techway.dto.ProductDto;
import com.techway.service.IProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	IProductService productService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity< List<ProductDto>> list(@RequestParam("cid") Optional<Integer> cid) {
		List<ProductDto> list = null;
		if (cid.isPresent())
			list = productService.findAllByCategoryId(cid.get());
		else
			list = productService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}	
	
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> getOne(@PathVariable("id") long id) {
		return new ResponseEntity<>(productService.findById(id), HttpStatus.OK);
	}
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto) {
		return new ResponseEntity<>(productService.save(productDto), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> update(@PathVariable("id") long id, @RequestBody ProductDto productDto) {
		return new ResponseEntity<>(productService.update(id, productDto), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		productService.disable(id);
		return new ResponseEntity<String>("Product deleted successfully!", HttpStatus.OK);
	}
}
