package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.entity.Comment;
import com.techway.model.entity.User;
import com.techway.repository.CommentRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.UserRepository;
import com.techway.service.ICommentService;

@Service
public class CommentService implements ICommentService {
	
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;

	@Override
	public List<Comment> findByProductId(Long productId) {
		if (!productRepository.existsById(productId)) {
			throw new ResourceNotFoundException(String.format("Product Id %d not found", productId));
		}
		List<Comment> comments = commentRepository.findAllByProductId(productId);
		return comments;
	}

	@Override
	public Comment findBytId(Long id) {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(String.format("Comment Id %d not found", id)));
		return comment;
	}

	@Override
	public Comment save(Long userId, Long productId, Comment commentRequest) {
		Comment comment = productRepository.findById(productId).map(product -> {
			commentRequest.setProduct(product);
			User user = userRepository.findById(userId).get();
			commentRequest.setUser(user);
			return commentRepository.save(commentRequest);
		}).orElseThrow(() -> new ResourceNotFoundException(String.format("Product Id %d not found", productId)));
		return comment;
	}

}
