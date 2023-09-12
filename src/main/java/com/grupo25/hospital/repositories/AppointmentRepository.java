package com.grupo25.hospital.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.grupo25.hospital.models.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query(value = "SELECT * FROM appointment  WHERE appointment_time between :start and :end ", nativeQuery = true)
	List<Appointment> findAllByTimestampToday(@Param("start") LocalDate start, @Param("end") LocalDate end);


	List<Appointment> findAllByTimestampBetween(LocalDateTime timestamp_Start, LocalDateTime timestamp_End);
	
	@Query(value = "select *from appointment where id_patient = ?", nativeQuery = true)
	List<Appointment> patientAppointments(Long id);
	
	
	@Query(value = "select *from appointment where id_appointment = ?", nativeQuery = true)
	Appointment getAppointmentById(Long id);
	
}
