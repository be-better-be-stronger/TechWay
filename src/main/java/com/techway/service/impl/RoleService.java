package com.techway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.techway.exception.ResourceNotFoundException;
import com.techway.model.entity.Role;
import com.techway.repository.RoleRepository;
import com.techway.service.IRoleService;

public class RoleService implements IRoleService{
	
	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name).orElseThrow(
				() -> new ResourceNotFoundException(String.format("Role %s not found", name))
				);				
	}

	@Override
	public List<Role> listRoles() {
		return roleRepository.findAll();
	}

}
