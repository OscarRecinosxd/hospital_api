package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.dtos.CreateDrugDTO;
import com.grupo25.hospital.models.dtos.EditDrugDTO;
import com.grupo25.hospital.models.entities.Drug;

public interface DrugService {
	void insert(CreateDrugDTO drugInfo) throws Exception;
	void update(EditDrugDTO drugInfo, Drug drug) throws Exception;
	void delete(Drug drug) throws Exception;
	Drug findOneById(Long id) throws Exception;
	Drug findOneByIdentifier(String name) throws Exception;
	List<Drug> findAll() throws Exception;
}
