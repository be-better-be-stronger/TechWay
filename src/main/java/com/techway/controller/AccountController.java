package com.techway.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.dto.AuthRequest;
import com.techway.dto.AuthResponse;
import com.techway.dto.RegistrationDTO;
import com.techway.entity.Account;
import com.techway.jwt.JwtTokenUtil;
import com.techway.service.impl.AccountService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/accounts")
public class AccountController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	AccountService accountService;
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
             
            Account account = (Account) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(account);
            AuthResponse response = new AuthResponse(account.getEmail(), accessToken);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@PostMapping("/registration")
	public String processRegister(@RequestBody RegistrationDTO userForm, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        accountService.register(userForm, accountService.getSiteURL(request));       
        return "register_success";
    }
	
	@GetMapping("/verify")
	public boolean verifyUser(@PathParam("code") String code) {
	   return accountService.verify(code);
	}
	
//	@PostMapping("/resending-verify-code")
//	public ResponseEntity<String> resendVerifyCode(@RequestBody RegistrationDTO dto){
//		try {
//			accountService.resendVerifyCode(dto);
//			return new ResponseEntity<>("Success", HttpStatus.OK);
//		}catch(Exception e){
//			return new ResponseEntity<>("Fail", HttpStatus.BAD_REQUEST);
//		}
//	}
}
