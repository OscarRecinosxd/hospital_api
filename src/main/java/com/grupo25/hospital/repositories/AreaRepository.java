package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
	Area findByName(String name);
}
