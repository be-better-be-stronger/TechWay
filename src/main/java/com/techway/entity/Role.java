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
@Table(name = "roles")
public class Role  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;

	@OneToMany(mappedBy = "role")
	List<Authority> authorities;
}
