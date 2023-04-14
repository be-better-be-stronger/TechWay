package com.techway.service;

import java.util.List;

import com.techway.model.dto.ReplyCommentDto;
import com.techway.model.entity.ReplyComment;

public interface IReplyCommentService {

	List<ReplyComment> findAllByUserId(Long userId);

	ReplyCommentDto create(ReplyCommentDto dto);

	boolean delete(long id);

}
