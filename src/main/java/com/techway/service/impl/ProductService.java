package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.entity.Product;
import com.techway.repository.ProductRepository;
import com.techway.service.IProductService;
@Service
public class ProductService implements IProductService{
	
	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> findByCategoryId(String cid) {
		return productRepository.findByCategoryId(cid);
	}

	@Override
	public void delete(Integer id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public Product createOrUpdate(Product product) {
		return productRepository.save(product);
	}


	
	
	
}
