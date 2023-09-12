package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.dtos.CreateTestDTO;
import com.grupo25.hospital.models.dtos.EditTestDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Test;

public interface TestService {
	void insert(CreateTestDTO testInfo) throws Exception;
	void update(EditTestDTO testInfo, Test test) throws Exception;
	void delete(Test test) throws Exception;
	Test findOneById(Long id) throws Exception;
	Test findOneByIdentifier(String name) throws Exception;
	List<Test> findAll() throws Exception;
	List<Appointment> getTestByPatient(Long id_patient) throws Exception;
}
