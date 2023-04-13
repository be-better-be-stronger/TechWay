package com.techway.service;

import java.util.List;

import com.techway.model.entity.Role;

public interface IRoleService {
	Role findByName(String name);

	List<Role> listRoles();
}
