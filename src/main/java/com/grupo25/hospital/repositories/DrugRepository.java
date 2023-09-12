package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Drug;

public interface DrugRepository extends JpaRepository<Drug, Long> {
	Drug findByName(String name);
}
