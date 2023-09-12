package com.grupo25.hospital.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grupo25.hospital.models.entities.Appointment_type;

public interface AppointmentTypeRepository extends JpaRepository<Appointment_type, Long> {

}
