package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.model.entity.Comment;
import com.techway.service.ICommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

	@Autowired
	private ICommentService commentService;

	// retrieve comments of a product
	@GetMapping("/comments/product/{productId}")
	public ResponseEntity<List<Comment>> getAllCommentsByProductId(@PathVariable(value = "productId") Long productId) {
		return new ResponseEntity<>(commentService.findByProductId(productId), HttpStatus.OK);
	}

	// retrieve a Comment by :id
	@GetMapping("/{id}")
	public ResponseEntity<Comment> getOne(@PathVariable(value = "id") Long id) {
		Comment comment = commentService.findBytId(id);
		return new ResponseEntity<>(comment, HttpStatus.OK);
	}
	
	//create new Comment for a Product
	@PostMapping("/product/{id}")
	public ResponseEntity<Comment> createComment(@PathVariable(value = "id") Long productId,
			@RequestBody Comment commentRequest) {
		Comment comment = commentService.save(productId, commentRequest);
		return new ResponseEntity<>(comment, HttpStatus.CREATED);
	}
	
	

}
