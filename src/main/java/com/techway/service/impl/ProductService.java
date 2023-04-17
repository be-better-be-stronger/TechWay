package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.dto.request.ProductRequest;
import com.techway.model.dto.response.ProductResponse;
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
	public List<ProductResponse> findByNameContaining(String name) {
		List<ProductResponse> products = productRepository.findByNameContaining(name)
				.stream().map(product -> entityToProductResponse(product)).collect(Collectors.toList());
		return products;
	}
	
	@Override
	public List<ProductResponse> findAll() {
		List<ProductResponse> products = productRepository.findAll()
				.stream().map(product -> entityToProductResponse(product)).collect(Collectors.toList());
		return products;
	}
	
	@Override
	public List<ProductResponse> findByAvailable() {
		List<ProductResponse> products = productRepository.findByAvailable(true)
				.stream().map(product -> entityToProductResponse(product)).collect(Collectors.toList());
		return products;
	}

	@Override
	public ProductResponse findById(long id) {
		Product product = productRepository.findById(id).get();
		return entityToProductResponse(product);
	}

	@Override
	public List<ProductResponse> findAllByCategoryId(int cid) {
		List<Product> products = productRepository.findAllByCategoryId(cid);
		return products.stream().map(p->entityToProductResponse(p)).collect(Collectors.toList());
	}

	@Override
	public boolean disable(long id) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setAvailable(false);
		productRepository.save(product);
		return true;
	}

	@Override
	public ProductResponse save(ProductRequest productRequest) {
		Product product = new Product();
		Product savedProduct = productRepository.save(productDtoToEntity(productRequest, product));
		return entityToProductResponse(savedProduct);
	}
	
	@Override
	public ProductResponse update(long id, ProductRequest productRequest) {
		Product product = productRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Product Id %d not found", id))
				);
		product.setProductNo(productRequest.getProductNo());
		product.setName(productRequest.getName());
		product.setImages(productRequest.getImage());
		product.setPrice(productRequest.getPrice());
		product.setAvailable(productRequest.getAvailable());
		product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productRequest.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productRequest.getColorId()).get());
		productRepository.save(product);		
		return entityToProductResponse(product);
	}	

	private Product productDtoToEntity(ProductRequest productRequest, Product product) {
		product.setProductNo(productRequest.getProductNo());
		product.setName(productRequest.getName());
		product.setImages(productRequest.getImage());
		product.setPrice(productRequest.getPrice());
		product.setAvailable(productRequest.getAvailable());
		product.setCreatedDate(productRequest.getCreatedDate());
		product.setCategory(categoryRepository.findById(productRequest.getCategoryId()).get());
		product.setManufacturer(manufacturerRepository.findById(productRequest.getManufacturerId()).get());
		product.setColor(colorRepository.findById(productRequest.getColorId()).get());
		return product;
	}
	
//	private ProductRequest entityToProductDto(Product savedProduct) {
//		ProductRequest productRequest = new ProductRequest();
//		productRequest.setId(savedProduct.getId());
//		productRequest.setName(savedProduct.getName());
//		productRequest.setImage(savedProduct.getImages());
//		productRequest.setPrice(savedProduct.getPrice());
//		productRequest.setAvailable(savedProduct.getAvailable());
//		productRequest.setCreatedDate(savedProduct.getCreatedDate());
//		productRequest.setCategoryId(savedProduct.getCategory().getId());
//		productRequest.setManufacturerId(savedProduct.getManufacturer().getId());
//		productRequest.setColorId(savedProduct.getColor().getId());
//		return  productRequest;
//	}
	
	private ProductResponse entityToProductResponse(Product entity) {
		ProductResponse response = new ProductResponse();
		response.setId(entity.getId());
		response.setProductNo(entity.getProductNo());
		response.setName(entity.getName());
		response.setImages(entity.getImages());
		response.setPrice(entity.getPrice());
		response.setAvailable(entity.getAvailable());
		response.setCreatedDate(entity.getCreatedDate());
		response.setCategory(entity.getCategory());
		response.setManufacturer(entity.getManufacturer());
		response.setColor(entity.getColor());
		return  response;
	}

	

}
