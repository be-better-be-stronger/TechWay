package com.techway.service;

import javax.servlet.http.HttpServletRequest;

public interface IAccountService {

	boolean verify(String verificationCode);

	String getSiteURL(HttpServletRequest request);
	

}
