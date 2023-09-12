package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

public class UserRecordDTO {
	private int appointment_type;
	private LocalDate appointment_time;
	private String appointment_detail;
	public UserRecordDTO(int appointment_type, LocalDate appointment_time, String appointment_detail) {
		super();
		this.appointment_type = appointment_type;
		this.appointment_time = appointment_time;
		this.appointment_detail = appointment_detail;
	}
	public int getAppointment_type() {
		return appointment_type;
	}
	public void setAppointment_type(int appointment_type) {
		this.appointment_type = appointment_type;
	}
	public LocalDate getAppointment_time() {
		return appointment_time;
	}
	public void setAppointment_time(LocalDate appointment_time) {
		this.appointment_time = appointment_time;
	}
	public String getAppointment_detail() {
		return appointment_detail;
	}
	public void setAppointment_detail(String appointment_detail) {
		this.appointment_detail = appointment_detail;
	}
}
