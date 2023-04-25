package com.techway.controller;

import java.util.List;

import com.techway.createAnnotation.CurrentUser;
import com.techway.entity.Comment;
import com.techway.security.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.techway.service.CommentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // retrieve comments of a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Comment>> getAllCommentsByProductId(@PathVariable(value = "productId") Long productId) {
        return new ResponseEntity<>(commentService.findByProductId(productId), HttpStatus.OK);
    }

    // retrieve a Comment by :id
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getOne(@PathVariable(value = "id") Long id,
	@CurrentUser UserDetailsImpl loginUser) {
        Comment comment = commentService.findBytId(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    //create new Comment for a Product
    @PostMapping("/product/{id}")
    public ResponseEntity<Comment> createComment(@PathVariable("userId") Long userId, @PathVariable(value = "id") Long productId,
                                                 @RequestBody Comment commentRequest) {
        Comment comment = commentService.save(userId, productId, commentRequest);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }
   
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Integer> deleteComment(@PathVariable(value = "id") Long productId,
                                                 @CurrentUser UserDetailsImpl loginUser) {
    	System.out.println("userId: " +  loginUser.getId());
        Integer deletedCommentStatus = commentService.delete(loginUser.getId(), productId);
        return new ResponseEntity<>(deletedCommentStatus, HttpStatus.OK);
    }

    @GetMapping("/user")
    public String getUser(Authentication authentication) {
        String username = authentication.getName();// Lấy tên đăng nhập của người dùng
        return "Xin chào " + username;
    }
}
