package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.dto.ReplyCommentDto;
import com.techway.model.entity.Comment;
import com.techway.model.entity.ReplyComment;
import com.techway.model.entity.User;
import com.techway.repository.CommentRepository;
import com.techway.repository.ReplyCommentRepository;
import com.techway.repository.UserRepository;
import com.techway.service.IReplyCommentService;

@Service
public class ReplyCommentService implements IReplyCommentService{
	@Autowired
	ReplyCommentRepository replyCommentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public List<ReplyComment> findAllByUserId(Long userId) {
		return replyCommentRepository.findAllByUserId(userId);
	}
	
	@Override
	public ReplyCommentDto create(ReplyCommentDto dto) {
		ReplyComment reply = mapDtoToEntity(dto);
		return mapEntityToDto(replyCommentRepository.save(reply));
	}
	

	@Override
	public boolean delete(long id) {
		try {
			ReplyComment reply = replyCommentRepository.findById(id).get();
			replyCommentRepository.delete(reply);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private ReplyComment mapDtoToEntity(ReplyCommentDto request) {
		ReplyComment response = new ReplyComment();
		Comment comment = commentRepository.findById(request.getCommentId()).get();
		User user = userRepository.findById(request.getUserId()).get();		
		response.setContent(request.getContent());
		response.setComment(comment);
		response.setUser(user);
		return response;
	}

	private ReplyCommentDto mapEntityToDto(ReplyComment request) {
		ReplyCommentDto response = new ReplyCommentDto();
		response.setId(request.getId());
		response.setContent(request.getContent());
		response.setCommentId(request.getComment().getId());
		response.setUserId(request.getUser().getId());
		return response;
	}

}
