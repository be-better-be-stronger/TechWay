package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	List<Comment> findAllByProductId(Long productId);

}
