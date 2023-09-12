package com.grupo25.hospital.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.CreateDrugDTO;
import com.grupo25.hospital.models.dtos.EditDrugDTO;
import com.grupo25.hospital.models.entities.Drug;
import com.grupo25.hospital.repositories.DrugRepository;
import com.grupo25.hospital.services.DrugService;

@Service
public class DrugServiceImpl implements DrugService {
	@Autowired
	private DrugRepository drugRepo;

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insert(CreateDrugDTO drugInfo) throws Exception {
		Drug drug = new Drug();
		
		if(drugInfo.getDrug_lab() != null) drug.setDrug_lab(drugInfo.getDrug_lab());
		if(drugInfo.getName() != null) drug.setName(drugInfo.getName());
		drug.setActive(drugInfo.getActive());
		drug.setActive_percentage(drugInfo.getActive_percentage());
		
		drugRepo.save(drug);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(EditDrugDTO drugInfo, Drug drug) throws Exception {
		if(drugInfo.getDrug_lab() != null) drug.setDrug_lab(drugInfo.getDrug_lab());
		if(drugInfo.getName() != null) drug.setName(drugInfo.getName());
		if(drugInfo.getActive() != null) drug.setActive(drugInfo.getActive());
		if(drugInfo.getActive_percentage() != null) drug.setActive_percentage(drugInfo.getActive_percentage());
		
		drugRepo.save(drug);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(Drug drug) throws Exception {
		drugRepo.delete(drug);
	}

	@Override
	public Drug findOneById(Long id) throws Exception {
		return drugRepo.findById(id).orElse(null);
	}

	@Override
	public Drug findOneByIdentifier(String name) throws Exception {
		return drugRepo.findByName(name);
	}

	@Override
	public List<Drug> findAll() throws Exception {
		return drugRepo.findAll();
	}
}
