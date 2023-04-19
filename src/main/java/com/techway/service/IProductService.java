package com.techway.service;

import java.util.List;

import com.techway.model.dto.request.ProductRequest;
import com.techway.model.entity.Product;

public interface IProductService {	
	Product findById(long id);
	List<Product> findAllByCategoryId(int cid);
	Product save(ProductRequest productRequest);
	Product update(long id, ProductRequest productRequest);
	boolean disable(long id);
	List<Product> findByAvailable();
	List<Product> findByNameContaining(String name);
	List<Product> findAll();
}
