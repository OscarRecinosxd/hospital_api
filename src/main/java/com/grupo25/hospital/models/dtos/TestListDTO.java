package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

public class TestListDTO {
	private String name;
	private LocalDate appointment_time;
	public TestListDTO(String name, LocalDate appointment_time) {
		super();
		this.name = name;
		this.appointment_time = appointment_time;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(LocalDate appointment_time) {
		this.appointment_time = appointment_time;
	}
}
