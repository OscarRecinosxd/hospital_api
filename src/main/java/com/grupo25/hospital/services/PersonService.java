package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.dtos.UpdatePassDTO;
import com.grupo25.hospital.models.dtos.CreatePersonDTO;
import com.grupo25.hospital.models.dtos.EditPersonDTO;
import com.grupo25.hospital.models.dtos.RestorePassDTO;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Role;

public interface PersonService {
	void register(CreatePersonDTO personInfo, Role role, Area area) throws Exception;
	void update(EditPersonDTO personInfo, Person person) throws Exception;
	void deactivate(Person person, Boolean status) throws Exception;
	Person findOneById(Long id) throws Exception;
	Person findOneByIdentifier(String username) throws Exception;
	List<Person> findAll() throws Exception;
	Boolean comparePassword(Person person, String passToCompare) throws Exception;
	void insertToken(Person person, String token) throws Exception;
	Boolean isTokenValid(Person person, String token) throws Exception;
	Person getPersonAuthenticated() throws Exception;
	void updatePersonPassword(UpdatePassDTO passInfo, Person person) throws Exception;
	void restorePassword(RestorePassDTO restoreInfo, Person person) throws Exception;
}