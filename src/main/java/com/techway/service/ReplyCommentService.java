package com.techway.service;

import java.util.List;

import com.techway.dto.ReplyCommentDto;

public interface ReplyCommentService {

	List<ReplyCommentDto> findAllByUserId(Long userId);

	ReplyCommentDto create(ReplyCommentDto dto);

	boolean delete(long replyId);

	List<ReplyCommentDto> findAllByCommentId(Long commentId);

}
