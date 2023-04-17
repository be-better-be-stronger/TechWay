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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techway.model.dto.AccountDto;
import com.techway.model.dto.RegistrationDTO;
import com.techway.model.dto.request.AuthRequest;
import com.techway.model.dto.response.AuthResponse;
import com.techway.security.jwt.JwtTokenUtil;
import com.techway.security.UserDetailsImpl;
import com.techway.service.impl.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	@Autowired
	UserService userService;
	
	
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
             
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
            AuthResponse response = new AuthResponse(userDetails.getEmail(), accessToken);
             
            return ResponseEntity.ok().body(response);
             
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
	
	@PostMapping("/registration")
	public String processRegister(@RequestBody RegistrationDTO userForm, HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        userService.register(userForm, userService.getSiteURL(request));
        return "register_success";
    }
	
	@GetMapping("/verify")
	public boolean verifyUser(@PathParam("code") String code) {
	   return userService.verify(code);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AccountDto> updateAccount(@PathVariable("id") Long id, @RequestBody AccountDto dto) {
		AccountDto account = userService.findById(id);
		return new ResponseEntity<>(account, HttpStatus.OK);
	} 
}
