package com.grupo25.hospital.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.entities.Role;
import com.grupo25.hospital.repositories.RoleRepository;
import com.grupo25.hospital.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Role findOneById(Long id) throws Exception {
		return roleRepository.findById(id).orElse(null);
	}

	@Override
	public List<Role> findAll() throws Exception {
		return roleRepository.findAll();
	}

}
