package com.techway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.AccountDto;
import com.techway.service.UserService;
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> updateAccount(Authentication authn, @PathVariable("id") Long id, @RequestBody AccountDto dto) {
		String email = authn.getName();		
		return new ResponseEntity<>(userService.updateAccount(email, dto), HttpStatus.OK);
	} 
	
	@GetMapping
	public ResponseEntity<List<AccountDto>> getAllAccounts() {
		List<AccountDto> accounts = userService.findAll();
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<AccountDto> getOne(@PathVariable("id") Long id) {
		AccountDto account = userService.findById(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	} 
}
