package com.techway.service;

import java.util.List;

import com.techway.entity.Comment;

public interface CommentService {
//
	List<Comment> findByProductId(Long productId);

	Comment findBytId(Long id);

	Comment save(Long userId, Long productId, Comment commentRequest);

    Integer delete(Long userId, Long productId);
}
