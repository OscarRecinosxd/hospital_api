package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.dtos.CreateVaccineDTO;
import com.grupo25.hospital.models.dtos.EditVaccineDTO;
import com.grupo25.hospital.models.entities.Vaccine;

public interface VaccineService {
	void insert(CreateVaccineDTO vaccInfo) throws Exception;
	void update(EditVaccineDTO vaccInfo, Vaccine vacc) throws Exception;
	void delete(Vaccine vaccine) throws Exception;
	Vaccine findOneById(Long id) throws Exception;
	Vaccine findOneByIdentifier(String name) throws Exception;
	List<Vaccine> findAll() throws Exception;
}
