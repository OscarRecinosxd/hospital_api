package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

public class RemindersDTO {
	private LocalDate appointment_time;
	private String appointment_details;
	public RemindersDTO(LocalDate appointment_time, String appointment_details) {
		super();
		this.appointment_time = appointment_time;
		this.appointment_details = appointment_details;
	}
	public LocalDate getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(LocalDate appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getAppointment_details() {
		return appointment_details;
	}
	public void setAppointment_details(String appointment_details) {
		this.appointment_details = appointment_details;
	}
	
	

}
