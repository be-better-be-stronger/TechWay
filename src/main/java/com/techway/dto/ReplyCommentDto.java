package com.techway.dto;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCommentDto {
	private long id;
	private String content;
	private Date created_at;
	private long userId;
	private long commentId;
}
