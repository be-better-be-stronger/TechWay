package com.techway.service;

public interface IAccountService {

	boolean verify(String email, String verificationCode);
	

}
