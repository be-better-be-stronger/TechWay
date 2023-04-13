package com.techway.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.techway.model.dto.AccountDto;
import com.techway.model.entity.User;

public interface IUserService {

	boolean verify(String verificationCode);
	String getSiteURL(HttpServletRequest request);
	List<AccountDto> findAll();
	AccountDto get(Long id);
	AccountDto updateAccount(Long accountId, AccountDto account);
	User findByEmail(String email);
	
}
