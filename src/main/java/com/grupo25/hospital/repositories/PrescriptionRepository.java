package com.grupo25.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo25.hospital.models.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long>{
		
	@Query(value = "select * from prescription where id_appointment = ? ", nativeQuery = true)
	List<Prescription> getPrescriptionsByIdAppointment(Long id_prescription);
}
