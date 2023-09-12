package com.grupo25.hospital.models.dtos;

public class AppoinmentIdDTO {
	private Long id_appointment;

	public AppoinmentIdDTO(Long id_appointment) {
		super();
		this.id_appointment = id_appointment;
	}

	public AppoinmentIdDTO() {
		super();
	}

	public Long getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}
	
	
}
