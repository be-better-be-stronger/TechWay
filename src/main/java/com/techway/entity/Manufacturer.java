package com.techway.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "manufacturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String manufacturerName;
	
	@JsonIgnore
	@OneToMany(mappedBy = "manufacturer")
	private List<Product> products;

}
