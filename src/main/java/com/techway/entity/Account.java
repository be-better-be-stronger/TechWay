package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique = true, nullable = false, length = 255)
	private String email;
	@Column(unique = true, nullable = false)
	private String username;
	@Column(nullable = false)
	private String password;
	private String fullname;	
	private String photo;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	List<Comment> comments;
}
