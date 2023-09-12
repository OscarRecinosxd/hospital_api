package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.entities.Role;

public interface RoleService {
	Role findOneById(Long id) throws Exception;
	List<Role> findAll() throws Exception;
}
