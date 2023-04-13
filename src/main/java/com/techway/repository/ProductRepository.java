package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategoryId(int cid);
}
