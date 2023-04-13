package com.techway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techway.model.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	Optional<User> findByEmail(String email);
	
	@Query("SELECT u FROM Account u WHERE u.verificationCode = ?1")
    public User findByVerificationCode(String code);
	
	Boolean existsByEmail(String email);
	
}