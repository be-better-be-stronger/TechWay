package com.techway.service;

import java.util.List;

import com.techway.dto.ReplyCommentDto;

public interface ReplyCommentService {

	List<ReplyCommentDto> findAllByUserId(Long userId);

	boolean delete(String email, long replyId);

	List<ReplyCommentDto> findAllByCommentId(Long commentId);

	ReplyCommentDto create(String email, ReplyCommentDto dto);

}
