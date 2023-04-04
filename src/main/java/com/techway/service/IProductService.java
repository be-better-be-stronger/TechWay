package com.techway.service;

import java.util.List;

import com.techway.dto.ProductDto;

public interface IProductService {	
	List<ProductDto> findAll();
	ProductDto findById(long id);
	List<ProductDto> findAllByCategoryId(int cid);
	ProductDto save(ProductDto productDto);
	ProductDto update(long id, ProductDto productDto);
	void delete(long id);
}
