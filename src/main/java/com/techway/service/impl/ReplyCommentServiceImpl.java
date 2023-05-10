package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techway.RoleName;
import com.techway.dto.ReplyCommentDto;
import com.techway.entity.Comment;
import com.techway.entity.ReplyComment;
import com.techway.entity.Role;
import com.techway.entity.User;
import com.techway.repository.CommentRepository;
import com.techway.repository.ReplyCommentRepository;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.ReplyCommentService;

@Service
public class ReplyCommentServiceImpl implements ReplyCommentService{
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
	public ReplyCommentDto create(String email, ReplyCommentDto dto) {
		dto.setCreatedBy(email);
		ReplyComment reply = mapDtoToEntity(dto);		
		return mapEntityToDto(replyCommentRepository.save(reply));
	}
	

	@Override
	@Transactional
	public boolean delete(String email, long replyId) {	

		Role role = roleRepository.findByName(String.valueOf(RoleName.ROLE_DIRE)).get();
		try {
			User user = userRepository.findByEmail(email).get();
			Long userId = user.getId();
			if (user.getRoles().contains(role)) {
				replyCommentRepository.deleteById(replyId);
			}else {
				replyCommentRepository.deleteByUserIdAndId(userId, replyId);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	private ReplyComment mapDtoToEntity(ReplyCommentDto request) {
		ReplyComment response = new ReplyComment();
		Comment comment = commentRepository.findById(request.getCommentId()).get();
		User user = userRepository.findByEmail(request .getCreatedBy()).get();
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
		response.setCreatedBy(request.getUser().getFullname());
		return response;
	}
	
}
