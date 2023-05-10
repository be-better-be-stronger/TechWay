package com.techway.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.techway.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	
	@Query("SELECT c FROM Comment c WHERE c.user.email = :email AND c.id = :commentId")
    Optional<Comment> findByEmailAndCommentId(@Param("email") String email, @Param("commentId") Long commentId);

//	@Modifying
//	@Query("delete from Comment c where c.user = :user and c.product = :product")
//	Integer deleteByUserAndProduct(@Param("user") User user, @Param("product") Product product);

	List<Comment> findAllByProductId(Long productId);
	
	void deleteByProduct_Id(Long productId);
	
	@Modifying
    @Query("DELETE FROM Comment c WHERE c.user.email = :email")
    void deleteByUser_Email(String email);

}
