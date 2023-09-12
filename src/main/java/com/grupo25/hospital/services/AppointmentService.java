package com.grupo25.hospital.services;

import java.time.LocalDateTime;
import java.util.List;

import com.grupo25.hospital.models.dtos.EndAppointmentDTO;
import com.grupo25.hospital.models.dtos.ScheduleAppointmentDTO;
import com.grupo25.hospital.models.dtos.SecretaryScheduleAppointmentDTO;

import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Appointment_type;
import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Test;
import com.grupo25.hospital.models.entities.Vaccine;

public interface AppointmentService {
	void registerInmu(ScheduleAppointmentDTO newSchedule,Appointment_type type,Vaccine vaccine, Person person) throws Exception;
	void registerArea(ScheduleAppointmentDTO newSchedule,Appointment_type type,Area area, Person person) throws Exception;
	void registerTest(ScheduleAppointmentDTO newSchedule,Appointment_type type,Test test, Person person) throws Exception;
	
	void registerSInmu(SecretaryScheduleAppointmentDTO newSchedule,Appointment_type type,Vaccine vaccine, Person person) throws Exception;
	void registerSArea(SecretaryScheduleAppointmentDTO newSchedule,Appointment_type type,Area area, Person person) throws Exception;
	void registerSTest(SecretaryScheduleAppointmentDTO newSchedule,Appointment_type type,Test test, Person person) throws Exception;
	void endUpAppointment(Appointment appointment,EndAppointmentDTO details,Person doctor) throws Exception;
	void insertAppointmentDetails(Appointment appointment, String details) throws Exception;
	List<Appointment> findTodayAppointments(LocalDateTime timestamp,LocalDateTime timestamp2) throws Exception;
	
	List<Appointment> getPrevAppointments(Long id_patient) throws Exception;
	
	List<Appointment> getNextAppointments(Long id_patient) throws Exception;
	
	List<Appointment> getAllAppointments(Long id_patient) throws Exception;
	
	Appointment getById(Long id) throws Exception;
	
}