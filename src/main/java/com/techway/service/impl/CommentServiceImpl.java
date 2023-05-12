 package com.techway.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techway.RoleName;
import com.techway.dto.CommentDto;
import com.techway.entity.Comment;
import com.techway.entity.Product;
import com.techway.entity.Role;
import com.techway.entity.User;
import com.techway.exception.APIException;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.CommentRepository;
import com.techway.repository.ProductRepository;
import com.techway.repository.RoleRepository;
import com.techway.repository.UserRepository;
import com.techway.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired RoleRepository roleRepository;

    @Override
    public List<CommentDto> findByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException(String.format("Product Id %d not found", productId));
        }
        List<CommentDto> comments = commentRepository.findAllByProductId(productId).stream().map(
        		comment -> fromEntity(comment)).collect(Collectors.toList()
        );
        return comments;
    }

    @Override
    public Comment findBytId(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Comment Id %d not found", id)));
        return comment;
    }

    @Override
    public CommentDto save(String email, Long productId, CommentDto commentRequest) {
    	Comment comment = new Comment();
    	commentRequest.setCreatedBy(email);
    	commentRequest.setProductId(productId);
    	Comment savedComment = commentRepository.save(toEntity(commentRequest, comment));
        return fromEntity(savedComment);
    }

    @Override
    @Transactional
    public Boolean delete(String email, Long commentId) {
        
        User user = userRepository.findByEmail(email).get();
        
        Role roleDire = roleRepository.findByName(String.valueOf(RoleName.ROLE_DIRE)).get();
		if(!user.getRoles().contains(roleDire)) {
			Comment comment = commentRepository.findByEmailAndCommentId(email, commentId).orElseThrow(
	        		() -> new APIException(String.format("User with email %s are not permitted to delete comment with id %d", 
	        				email, commentId))
	        		);
			System.out.println(fromEntity(comment));
		}		
		commentRepository.deleteById(commentId);       
       return true;
    }
    
    public CommentDto fromEntity(Comment comment) {
	    CommentDto commentDto = new CommentDto();
	    commentDto.setId(comment.getId());
	    commentDto.setContent(comment.getContent());
	    commentDto.setCreatedBy(comment.getUser().getFullname());
	    commentDto.setCreatedDate(comment.getCreatedDate());
	    return commentDto;
	}
	
	public  Comment toEntity(CommentDto commentDto, Comment comment) {
	    comment.setContent(commentDto.getContent());
	    comment.setUser(userRepository.findByEmail(commentDto.getCreatedBy()).get());
	    Product product = productRepository.findById(commentDto.getProductId()).get();
	    comment.setProduct(product);
	    comment.setCreatedDate(commentDto.getCreatedDate());
	    return comment;
	}

}
