package com.grupo25.hospital.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.CreateTestDTO;
import com.grupo25.hospital.models.dtos.EditTestDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Test;
import com.grupo25.hospital.repositories.AppointmentRepository;
import com.grupo25.hospital.repositories.TestRepository;
import com.grupo25.hospital.services.TestService;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insert(CreateTestDTO testInfo) throws Exception {
		Test test = new Test();
		
		test.setName(testInfo.getName());
		if(testInfo.getGender() != null) test.setGender(testInfo.getGender());
		if(testInfo.getStart_age() != null) test.setStart_age(testInfo.getStart_age());
		if(testInfo.getFrequency() != null) test.setFrequency(testInfo.getFrequency());
		
		testRepo.save(test);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(EditTestDTO testInfo, Test test) throws Exception {
		if(testInfo.getName() != null) test.setName(testInfo.getName());
		if(testInfo.getGender() != null) test.setGender(testInfo.getGender());
		if(testInfo.getStart_age() != null) test.setStart_age(testInfo.getStart_age());
		if(testInfo.getFrequency() != null) test.setFrequency(testInfo.getFrequency());
		
		testRepo.save(test);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(Test test) throws Exception {
		testRepo.delete(test);
	}

	@Override
	public Test findOneById(Long id) throws Exception {
		return testRepo.findById(id).orElse(null);
	}

	@Override
	public Test findOneByIdentifier(String name) throws Exception {
		return testRepo.findByName(name);
	}

	@Override
	public List<Test> findAll() throws Exception {
		return testRepo.findAll();
	}

	@Override
	public List<Appointment> getTestByPatient(Long id_patient) throws Exception {
		
		List<Appointment> appointments = appointmentRepository.patientAppointments(id_patient);
		List<Appointment> testListAppointments = new ArrayList<>();
		
				
		appointments.forEach(Appointment ->{
			if(Appointment.getId_appointment_type().getId_appointment_type() == 3 
					&& Appointment.getStatus()
					){
				testListAppointments.add(Appointment);
			}
		});
		
		return testListAppointments;
	}

}
