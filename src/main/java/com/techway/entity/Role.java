package com.techway.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String roleNo;
	
	@Column(nullable = false, length = 20, unique = true)
    private String name;
	
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
