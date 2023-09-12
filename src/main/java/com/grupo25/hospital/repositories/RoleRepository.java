package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
