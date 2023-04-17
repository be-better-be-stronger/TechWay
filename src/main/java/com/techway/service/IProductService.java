package com.techway.service;

import java.util.List;

import com.techway.model.dto.request.ProductRequest;
import com.techway.model.dto.response.ProductResponse;

public interface IProductService {	
	ProductResponse findById(long id);
	List<ProductResponse> findAllByCategoryId(int cid);
	ProductResponse save(ProductRequest productRequest);
	ProductResponse update(long id, ProductRequest productRequest);
	boolean disable(long id);
	List<ProductResponse> findByAvailable();
	List<ProductResponse> findByNameContaining(String name);
	List<ProductResponse> findAll();
}
