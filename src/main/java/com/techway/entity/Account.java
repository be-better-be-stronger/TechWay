package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
@Table(name = "accounts")
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	String username;
	String password;
	String fullname;
	String email;
	String photo;
	
	@OneToMany(mappedBy = "account")
	List<Order> orders;
	
	@OneToMany(mappedBy = "account")
	List<Authority> authorities;
	
	@OneToMany(mappedBy = "account")
	List<Comment> comments;
}
