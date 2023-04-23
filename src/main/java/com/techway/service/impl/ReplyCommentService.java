package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techway.dto.ReplyCommentDto;
import com.techway.entity.Comment;
import com.techway.entity.ReplyComment;
import com.techway.entity.User;
import com.techway.repository.CommentRepository;
import com.techway.repository.ReplyCommentRepository;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.IReplyCommentService;

@Service
public class ReplyCommentService implements IReplyCommentService{
//	@Autowired
//	HttpServletRequest requestServlet;
	@Autowired
	ReplyCommentRepository replyCommentRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	RoleRepository roleRepository;
	
	
//	String email = requestServlet.getUserPrincipal().getName();
	
	@Override
	public List<ReplyCommentDto> findAllByUserId(Long userId) {
		return replyCommentRepository.findAllByUserId(userId).stream().map(
				reply -> mapEntityToDto(reply)
				).collect(Collectors.toList());
	}
	
	@Override
	public List<ReplyCommentDto> findAllByCommentId(Long commentId) {
		return replyCommentRepository.findAllByUserId(commentId).stream().map(
				reply -> mapEntityToDto(reply)
				).collect(Collectors.toList());
	}
	
	@Override
	public ReplyCommentDto create(ReplyCommentDto dto) {
		ReplyComment reply = mapDtoToEntity(dto);
		return mapEntityToDto(replyCommentRepository.save(reply));
	}
	

	@Override
	public boolean delete(long replyId) {	
//		User user = userRepository.findByEmail().get();
		User user = new User();
		ReplyComment reply = replyCommentRepository.findById(replyId).get();
		if((user.getId() == reply.getUser().getId()) || 
				user.getRoles().contains(roleRepository.findByName("ROLE_DIRE").get())) {
			replyCommentRepository.delete(reply);
			return true;
		}else
			return false;
	}
	
	private ReplyComment mapDtoToEntity(ReplyCommentDto request) {
		ReplyComment response = new ReplyComment();
		Comment comment = commentRepository.findById(request.getCommentId()).get();
//		User user = userRepository.findByEmail(email).get();
		User user = new User();
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
