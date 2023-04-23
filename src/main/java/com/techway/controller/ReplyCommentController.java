package com.techway.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.ReplyCommentDto;
import com.techway.repository.CommentRepository;
import com.techway.repository.UserRepository;
import com.techway.service.IReplyCommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/replies")
public class ReplyCommentController {

	@Autowired
	IReplyCommentService replyCommentService;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ReplyCommentDto>> getCommentsByUserId(@PathVariable("userId") Long userId){
		List<ReplyCommentDto> list = replyCommentService.findAllByUserId(userId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/comment/{commentId}")
	public ResponseEntity<List<ReplyCommentDto>> getCommentsByCommentId(@PathVariable("commentId") Long commentId, Principal principal){
		List<ReplyCommentDto> list = replyCommentService.findAllByCommentId(commentId);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping()
	public ResponseEntity<ReplyCommentDto> replyToCustomer(@RequestBody ReplyCommentDto dto){
		ReplyCommentDto reply = replyCommentService.create(dto);
		return new ResponseEntity<ReplyCommentDto>(reply, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{replyId}")
	public ResponseEntity<Boolean> deleteReply(@PathVariable("replyId") long replyId){
		return new ResponseEntity<Boolean>(replyCommentService.delete(replyId), HttpStatus.OK);
	}
	
	
}
