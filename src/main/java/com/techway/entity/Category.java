package com.techway.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "categories")
public class Category implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "category")
	private List<Product> products;
}
