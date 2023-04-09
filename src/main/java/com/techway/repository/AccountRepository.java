package com.techway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.techway.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Long> {
	
	Optional<Account> findByEmail(String email);

	@Query("SELECT u FROM Account u WHERE email = ?1 and u.verificationCode = ?2")
	public Account findByVerificationCodeAndEmail(String email, String verificationCode);

	
}
