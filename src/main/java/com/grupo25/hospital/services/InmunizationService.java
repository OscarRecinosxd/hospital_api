package com.grupo25.hospital.services;

import java.util.List;

import com.grupo25.hospital.models.dtos.CreateInmunizationDTO;
import com.grupo25.hospital.models.dtos.EditInmunizationDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Inmunization;
import com.grupo25.hospital.models.entities.Vaccine;

public interface InmunizationService {
	void insert(CreateInmunizationDTO inmuInfo, Vaccine vaccine) throws Exception;
	void update(EditInmunizationDTO inmuInfo, Inmunization inmunization) throws Exception;
	void delete(Inmunization inmunization) throws Exception;
	Inmunization findOneById(Long id) throws Exception;
	List<Inmunization> findAll() throws Exception;
	List<Appointment> getAppointmentsByID(Long id) throws Exception;
}
