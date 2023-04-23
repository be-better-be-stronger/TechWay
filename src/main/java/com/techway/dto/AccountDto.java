package com.techway.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDto {
	private Long id;
	private String fullName;
	private String email;
	private String photo;
	private Set<String> roles = new HashSet<>();
}
