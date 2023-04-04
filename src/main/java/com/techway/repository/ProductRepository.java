package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategoryId(long cid);
}
