package com.techway.model.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
		
	@Column(nullable = false, length = 20, unique = true)
    private String name;
	
	@ManyToMany(mappedBy = "roles")
	@JsonIgnore
	private Set<User> users;
	
	public Role(String name) {
        this.name = name;
    }
     
    public Role(Integer id) {
        this.id = id;
    } 
 
    @Override
    public String toString() {
        return this.name;
    }

}