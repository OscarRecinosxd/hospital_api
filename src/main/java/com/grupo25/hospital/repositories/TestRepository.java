package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Test;

public interface TestRepository extends JpaRepository<Test, Long> {
	Test findByName(String name);
	
}
