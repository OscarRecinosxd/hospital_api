package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.entities.Appointment_type;

public interface AppointmentTypeService {
	Appointment_type findOneById(Long id) throws Exception;
	List<Appointment_type> findAll() throws Exception;
}
