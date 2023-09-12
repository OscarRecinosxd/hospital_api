package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Vaccine;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
	Vaccine findByName(String name);
}
