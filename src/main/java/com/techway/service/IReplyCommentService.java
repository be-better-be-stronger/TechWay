package com.techway.service;

import java.util.List;

import com.techway.model.dto.ReplyCommentDto;

public interface IReplyCommentService {

	List<ReplyCommentDto> findAllByUserId(Long userId);

	ReplyCommentDto create(ReplyCommentDto dto);

	boolean delete(long replyId);

	List<ReplyCommentDto> findAllByCommentId(Long commentId);

}
