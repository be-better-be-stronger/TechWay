package com.techway.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techway.model.ERole;
import com.techway.model.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
	Optional<Role> findByName(ERole name);
}
