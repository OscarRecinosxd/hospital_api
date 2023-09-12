package com.grupo25.hospital.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.entities.Appointment_type;
import com.grupo25.hospital.repositories.AppointmentTypeRepository;
import com.grupo25.hospital.services.AppointmentTypeService;

@Service
public class AppontmentTypeServiceImpl implements AppointmentTypeService {

	@Autowired
	private AppointmentTypeRepository appRepo;
	
	@Override
	public Appointment_type findOneById(Long id) throws Exception {
		return appRepo.findById(id).orElse(null);
	}

	@Override
	public List<Appointment_type> findAll() throws Exception {
		return appRepo.findAll();
	}

}
