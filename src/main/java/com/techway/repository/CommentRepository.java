package com.techway.repository;

import java.util.List;

import com.techway.model.entity.Product;
import com.techway.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.Comment;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Modifying
	@Query("delete from Comment c where c.user = :user and c.product = :product")
	Integer deleteByUserAndProduct(@Param("user") User user, @Param("product") Product product);

	List<Comment> findAllByProductId(Long productId);

}
