package com.techway.service;

import java.util.List;

import com.techway.dto.CommentDto;
import com.techway.entity.Comment;

public interface CommentService {
//
	List<CommentDto> findByProductId(Long productId);

	Comment findBytId(Long id);

	CommentDto save(String email, Long productId, CommentDto commentRequest);

    Boolean delete(String email, Long commentId);
}
