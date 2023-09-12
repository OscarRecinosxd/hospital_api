package com.grupo25.hospital.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo25.hospital.models.dtos.CreatePrescriptionDTO;
import com.grupo25.hospital.models.dtos.PrescriptionDTO;
import com.grupo25.hospital.models.dtos.PrescriptionInfoDTO;
import com.grupo25.hospital.models.entities.Appointment;
import com.grupo25.hospital.models.entities.Drug;
import com.grupo25.hospital.models.entities.Inmunization;
import com.grupo25.hospital.models.entities.Person;
import com.grupo25.hospital.models.entities.Prescription;
import com.grupo25.hospital.repositories.AppointmentRepository;
import com.grupo25.hospital.repositories.PersonRepository;
import com.grupo25.hospital.repositories.PrescriptionRepository;
import com.grupo25.hospital.services.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService{

	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	
	@Override
	@Transactional(rollbackOn = Exception.class)
	public void insert(Person person,Appointment appointment,Drug drug, PrescriptionInfoDTO prescription) throws Exception {
		Prescription presc = new Prescription();
		presc.setId_appointment(appointment);
		presc.setId_drug(drug);
		presc.setIndication(prescription.getIndication());
		presc.setDaily_amount(prescription.getDoses());
		presc.setQuantity(prescription.getQuantity());
		
		prescriptionRepository.save(presc);
		
	}
	
	@Override
	public List<Prescription> getPatientPrescriptions(Long id_patient) throws Exception {
		
		List<Appointment> appointments = appointmentRepository.patientAppointments(id_patient);
		List<Prescription> allPrescriptions = new ArrayList<>();
		
		appointments.forEach( appointment ->{
			
			List<Prescription> a = prescriptionRepository
					.getPrescriptionsByIdAppointment(appointment.getId_appointment());
				System.out.println("Correctoxd");
				a.forEach(b -> {
					allPrescriptions.add(b);

				});

		
		});
		
		
		return allPrescriptions;
	}

	@Override
	@Transactional(rollbackOn = Exception.class)
	public void create(Drug drug, PrescriptionDTO prescription, Appointment appointment) throws Exception {
		Prescription presc = new Prescription();
		presc.setId_appointment(appointment);
		presc.setId_drug(drug);
		presc.setIndication(prescription.getIndication());
		presc.setDaily_amount(prescription.getDoses());
		presc.setQuantity(prescription.getQuantity());
		
		prescriptionRepository.save(presc);	
	}


}