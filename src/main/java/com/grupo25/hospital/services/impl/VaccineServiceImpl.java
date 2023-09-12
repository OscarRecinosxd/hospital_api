package com.grupo25.hospital.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.CreateVaccineDTO;
import com.grupo25.hospital.models.dtos.EditVaccineDTO;
import com.grupo25.hospital.models.entities.Vaccine;
import com.grupo25.hospital.repositories.VaccineRepository;
import com.grupo25.hospital.services.VaccineService;

@Service
public class VaccineServiceImpl implements VaccineService {

	@Autowired
	private VaccineRepository vaccRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insert(CreateVaccineDTO vaccInfo) throws Exception {
		Vaccine vacc = new Vaccine();
		
		vacc.setName(vaccInfo.getName());
		vacc.setRequired_doses(vaccInfo.getRequired_doses());
		
		vaccRepository.save(vacc);
	}

	@Override
	public void update(EditVaccineDTO vaccInfo, Vaccine vacc) throws Exception {
		if(vaccInfo.getName() != null) vacc.setName(vaccInfo.getName());
		if(vaccInfo.getRequired_doses() != null) vacc.setRequired_doses(vaccInfo.getRequired_doses());
		
		vaccRepository.save(vacc);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(Vaccine vaccine) throws Exception {
		vaccRepository.delete(vaccine);
	}

	@Override
	public Vaccine findOneById(Long id) throws Exception {
		return vaccRepository.findById(id).orElse(null);
	}

	@Override
	public Vaccine findOneByIdentifier(String name) throws Exception {
		return vaccRepository.findByName(name);
	}

	@Override
	public List<Vaccine> findAll() throws Exception {
		return vaccRepository.findAll();
	}

}
