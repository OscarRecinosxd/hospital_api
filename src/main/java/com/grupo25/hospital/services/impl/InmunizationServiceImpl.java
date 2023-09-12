package com.grupo25.hospital.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.CreateInmunizationDTO;
import com.grupo25.hospital.models.dtos.EditInmunizationDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Inmunization;
import com.grupo25.hospital.models.entities.Vaccine;
import com.grupo25.hospital.repositories.AppointmentRepository;
import com.grupo25.hospital.repositories.InmunizationRepository;
import com.grupo25.hospital.services.InmunizationService;

@Service
public class InmunizationServiceImpl implements InmunizationService {

	@Autowired
	private InmunizationRepository inmuRepo;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insert(CreateInmunizationDTO inmuInfo, Vaccine vaccine) throws Exception {
		Inmunization inmu = new Inmunization();
		
		inmu.setId_vaccine(vaccine);
		inmu.setAge(inmuInfo.getAge());
		
		inmuRepo.save(inmu);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void update(EditInmunizationDTO inmuInfo, Inmunization inmunization) throws Exception {
		if(inmuInfo.getAge() != null) inmunization.setAge(inmuInfo.getAge());
		
		inmuRepo.save(inmunization);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void delete(Inmunization inmunization) throws Exception {
		inmuRepo.delete(inmunization);
	}

	@Override
	public Inmunization findOneById(Long id) throws Exception {
		return inmuRepo.findById(id).orElse(null);
	}

	@Override
	public List<Inmunization> findAll() throws Exception {
		return inmuRepo.findAll();
	}

	@Override
	public List<Appointment> getAppointmentsByID(Long id_patient) throws Exception {
		List<Appointment> appointments = appointmentRepository.patientAppointments(id_patient);
		List<Appointment> inmunizationsAppointments = new ArrayList<>();
		
				
		appointments.forEach(Appointment ->{
			if(Appointment.getId_appointment_type().getId_appointment_type() == 1){
				inmunizationsAppointments.add(Appointment);
			}
		});
		
		
		return inmunizationsAppointments;

	}

}
