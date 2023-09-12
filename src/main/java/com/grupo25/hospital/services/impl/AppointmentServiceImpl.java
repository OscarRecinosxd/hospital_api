package com.grupo25.hospital.services.impl;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.EndAppointmentDTO;
import com.grupo25.hospital.models.dtos.ScheduleAppointmentDTO;
import com.grupo25.hospital.models.dtos.SecretaryScheduleAppointmentDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Appointment_type;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Test;
import com.grupo25.hospital.models.entities.Vaccine;
import com.grupo25.hospital.repositories.AppointmentRepository;
import com.grupo25.hospital.services.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	private AppointmentRepository appointmentRepo;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerInmu(ScheduleAppointmentDTO newSchedule, Appointment_type type,Vaccine vaccine, Person person)
			throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		appointment.setStatus(false);
		appointment.setId_vaccine(vaccine);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerArea(ScheduleAppointmentDTO newSchedule, Appointment_type type, Area area, Person person)
			throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:ss");

		appointment.setStatus(false);
		appointment.setId_area(area);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerTest(ScheduleAppointmentDTO newSchedule, Appointment_type type, Test test, Person person)
			throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		appointment.setStatus(false);
		appointment.setId_test(test);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerSInmu(SecretaryScheduleAppointmentDTO newSchedule, Appointment_type type, Vaccine vaccine,
			Person person) throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		appointment.setStatus(false);
		appointment.setId_vaccine(vaccine);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerSArea(SecretaryScheduleAppointmentDTO newSchedule, Appointment_type type, Area area,
			Person person) throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:m:ss");

		appointment.setStatus(false);
		appointment.setId_area(area);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);		
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void registerSTest(SecretaryScheduleAppointmentDTO newSchedule, Appointment_type type, Test test,
			Person person) throws Exception {
		Appointment appointment = new Appointment();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		appointment.setStatus(false);
		appointment.setId_test(test);
		appointment.setId_appointment_type(type);
		appointment.setId_patient(person);
		appointment.setTimestamp(LocalDateTime.parse(newSchedule.getDate(), formatter));
		
		appointmentRepo.save(appointment);		
	}

	@Override
	public List<Appointment> findTodayAppointments(LocalDateTime timestamp, LocalDateTime timestamp2) throws Exception {
		return appointmentRepo.findAllByTimestampBetween(timestamp, timestamp2);
	}
	
	@Override
	public List<Appointment> getPrevAppointments(Long id_patient) throws Exception {
		
		List<Appointment> appointments = appointmentRepo.patientAppointments(id_patient);
		
		
		List<Appointment> PrevAppointments = new ArrayList<>();
		
				
		appointments.forEach(Appointment ->{
			if(Appointment.getStatus().booleanValue() == true){
				PrevAppointments.add(Appointment);
			}
		});

		return PrevAppointments;
		
	}

	@Override
	public List<Appointment> getNextAppointments(Long id_patient) throws Exception {
		
		List<Appointment> appointments = appointmentRepo.patientAppointments(id_patient);
		
		List<Appointment> PrevAppointments = new ArrayList<>();
		
				
		appointments.forEach(Appointment ->{
			if(Appointment.getStatus().booleanValue() == false){
				PrevAppointments.add(Appointment);
			}
		});

		return PrevAppointments;
	}

	@Override
	public List<Appointment> getAllAppointments(Long id_patient) throws Exception {
		
		List<Appointment> Appointments = appointmentRepo.patientAppointments(id_patient);
		
		List<Appointment> AppointmentsAux = new ArrayList<>();
		
		Appointments.forEach(Appointment ->{
			
			
			if(Appointment.getStatus().booleanValue() == true){
				AppointmentsAux.add(Appointment);
			}
			
			
		});
		
		
		
		return AppointmentsAux;
	}

	@Override
	public Appointment getById(Long id) throws Exception {
		return appointmentRepo.getById(id);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void endUpAppointment(Appointment appointment,EndAppointmentDTO details,Person doctor) throws Exception {
		appointment.setStatus(true);
		if(details.getDescription() != null) appointment.setAppointment_details(details.getDescription());
		appointment.setId_doctor(doctor);
		appointmentRepo.save(appointment);
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insertAppointmentDetails(Appointment appointment, String details) throws Exception {
		appointment.setAppointment_details(details);
		appointmentRepo.save(appointment);
	}

	

	
}