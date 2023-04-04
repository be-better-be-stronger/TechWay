package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.ProductDto;
import com.techway.entity.Product;
import com.techway.exception.ProductNotFound;
import com.techway.repository.CategoryRepository;
import com.techway.repository.ColorRepository;
import com.techway.repository.ManufacturerRepository;
import com.techway.repository.ProductRepository;
import com.techway.service.IProductService;
@Service
public class ProductService implements IProductService{
	
	@Autowired
	ProductRepository productRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ManufacturerRepository manufacturerRepository;
	@Autowired
	ColorRepository colorRepository;


	@Override
	public List<ProductDto> findAll() {
		List<Product> products = productRepository.findAll();
		return products.stream().map(p->entityToProductDto(p)).collect(Collectors.toList());
	}

	@Override
	public ProductDto findById(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ProductNotFound(String.format("Product Id %d not found", id))
				);
		return entityToProductDto(product);
	}

	@Override
	public List<ProductDto> findAllByCategoryId(int cid) {
		List<Product> products = productRepository.findAllByCategoryId(cid);
		return products.stream().map(p->entityToProductDto(p)).collect(Collectors.toList());
	}

	@Override
	public void delete(long id) {
		productRepository.deleteById(id);
		
	}

	@Override
	public ProductDto save(ProductDto productDto) {
		Product product = productDtoToEntity(productDto);
		Product savedProduct = productRepository.save(product);
		return entityToProductDto(savedProduct);
	}
	
	@Override
	public ProductDto update(long id, ProductDto productDto) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ProductNotFound(String.format("Product Id %d not found", id))
				);
		product.setName(productDto.getName());
		product.setImage(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setAvailable(productDto.isAvailable());
		product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productDto.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productDto.getColorId()).get());
		productRepository.save(product);		
		return entityToProductDto(product);
	}	

	private Product productDtoToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setImage(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setAvailable(productDto.isAvailable());
		product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productDto.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productDto.getColorId()).get());
		return product;
	}
	
	private ProductDto entityToProductDto(Product savedProduct) {
		ProductDto productDto = new ProductDto();
		productDto.setId(savedProduct.getId());
		productDto.setName(savedProduct.getName());
		productDto.setImage(savedProduct.getImage());
		productDto.setPrice(savedProduct.getPrice());
		productDto.setAvailable(savedProduct.isAvailable());
		productDto.setCategoryId(savedProduct.getCategory().getId());
		productDto.setManufacturerId(savedProduct.getManufacturer().getId());
		productDto.setColorId(savedProduct.getColor().getId());
		return  productDto;
	}

	

	
	
	
}
