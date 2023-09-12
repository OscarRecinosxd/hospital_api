package com.grupo25.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	List<Token> findByPersonAndActive(Person person, Boolean active);
}
