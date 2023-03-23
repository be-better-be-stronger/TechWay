package com.techway.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.techway.entity.Product;
import com.techway.service.impl.ProductService;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public List<Product> list(@RequestParam("cid") Optional<String> cid) {
		if (cid.isPresent()) {
			List<Product> list = productService.findByCategoryId(cid.get());
			return  list;
		} else {
			List<Product> list = productService.findAll();
			return list;
		}
	}	
	
	@GetMapping("{id}")
	public Product getOne(@PathVariable("id") Integer id) {
		return productService.findById(id);
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return productService.createOrUpdate(product);
	}
	
	@PutMapping("{id}")
	public Product update(@PathVariable("id") Integer id, @RequestBody Product product) {
		return productService.createOrUpdate(product);
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable("id") Integer id) {
		productService.delete(id);
	}
}
