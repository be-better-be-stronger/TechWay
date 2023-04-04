package com.techway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProductNotFound extends RuntimeException{
	private String message;	
	public ProductNotFound(String message) {
		super(message);
		this.message = message;
	}
	
}
