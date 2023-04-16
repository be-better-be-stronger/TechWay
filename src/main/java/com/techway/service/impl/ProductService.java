package com.techway.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.dto.ProductDto;
import com.techway.model.entity.Product;
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
	public List<ProductDto> findByNameContaining(String name) {
		List<ProductDto> products = productRepository.findByNameContaining(name)
				.stream().map(product -> entityToProductDto(product)).collect(Collectors.toList());
		return products;
	}
	
	@Override
	public List<ProductDto> findAll() {
		List<ProductDto> products = productRepository.findAll()
				.stream().map(product -> entityToProductDto(product)).collect(Collectors.toList());
		return products;
	}
	
	@Override
	public List<ProductDto> findByAvailable() {
		List<ProductDto> products = productRepository.findByAvailable(true)
				.stream().map(product -> entityToProductDto(product)).collect(Collectors.toList());
		return products;
	}

	@Override
	public ProductDto findById(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		return entityToProductDto(product);
	}

	@Override
	public List<ProductDto> findAllByCategoryId(int cid) {
		List<Product> products = productRepository.findAllByCategoryId(cid);
		return products.stream().map(p->entityToProductDto(p)).collect(Collectors.toList());
	}

	@Override
	public void disable(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setAvailable(false);
		productRepository.save(product);
	}

	@Override
	public ProductDto save(ProductDto productDto) {
		Product product = productDtoToEntity(productDto);
		product.setCreatedDate(new Date());
		Product savedProduct = productRepository.save(product);
		return entityToProductDto(savedProduct);
	}
	
	@Override
	public ProductDto update(long id, ProductDto productDto) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setName(productDto.getName());
		product.setImages(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setAvailable(productDto.getAvailable());
		product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productDto.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productDto.getColorId()).get());
		productRepository.save(product);		
		return entityToProductDto(product);
	}	

	private Product productDtoToEntity(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setImages(productDto.getImage());
		product.setPrice(productDto.getPrice());
		product.setAvailable(productDto.getAvailable());
		product.setCreatedDate(productDto.getCreatedDate());
		product.setCategory(categoryRepository.findById(productDto.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productDto.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productDto.getColorId()).get());
		return product;
	}
	
	private ProductDto entityToProductDto(Product savedProduct) {
		ProductDto productDto = new ProductDto();
		productDto.setId(savedProduct.getId());
		productDto.setName(savedProduct.getName());
		productDto.setImage(savedProduct.getImages());
		productDto.setPrice(savedProduct.getPrice());
		productDto.setAvailable(savedProduct.getAvailable());
		productDto.setCreatedDate(savedProduct.getCreatedDate());
		productDto.setCategoryId(savedProduct.getCategory().getId());
		productDto.setManufacturerId(savedProduct.getManufacturer().getId());
		productDto.setColorId(savedProduct.getColor().getId());
		return  productDto;
	}

	

}
