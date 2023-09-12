package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
	Person findByUsername(String username);
}
