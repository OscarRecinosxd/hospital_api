package com.grupo25.hospital.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.grupo25.hospital.models.entities.Inmunization;

public interface InmunizationRepository extends JpaRepository<Inmunization, Long> {
	
	@Query(value = "select *from inmunization where id_inmunization = 1:id_inmunization", nativeQuery = true)
	List<Inmunization> getAppointmentsById(Long id_inmunization)throws Exception;
	
}
