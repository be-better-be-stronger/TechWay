package com.techway.model.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Nationalized;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "screenTechs")
public class ScreenTech implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Nationalized
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }, 
			mappedBy = "screenTechs")
	@JsonIgnore
	private Set<LaptopDetail> laptops = new HashSet<>();
}
