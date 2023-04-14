package com.techway.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.entity.ReplyComment;

public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Long>{
	
	List<ReplyComment> findAllByUserId(Long userId);

}
