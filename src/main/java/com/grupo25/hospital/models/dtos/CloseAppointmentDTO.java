package com.grupo25.hospital.models.dtos;

public class CloseAppointmentDTO {
	private int id_patient;
	private String observaciones;
	private boolean state;
	public CloseAppointmentDTO(int id_patient, String observaciones, boolean state) {
		super();
		this.id_patient = id_patient;
		this.observaciones = observaciones;
		this.state = state;
	}
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	
}
