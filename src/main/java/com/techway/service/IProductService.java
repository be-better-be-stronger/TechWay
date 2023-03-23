package com.techway.service;

import java.util.List;

import com.techway.entity.Product;

public interface IProductService {
	
	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategoryId(String cid);

	Product createOrUpdate(Product product);

	void delete(Integer id);
}
