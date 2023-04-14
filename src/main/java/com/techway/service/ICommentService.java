package com.techway.service;

import java.util.List;

import com.techway.model.entity.Comment;

public interface ICommentService {

	List<Comment> findByProductId(Long productId);

	Comment findBytId(Long id);

	Comment save(Long userId, Long productId, Comment commentRequest);

}
