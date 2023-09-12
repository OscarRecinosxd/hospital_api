package com.grupo25.hospital.models.dtos;

public class DocPendingAppointmentsDTO {
	private int id_doctor;
	private int id_patient;
	private String nombre;
	private int edad;
	private String genero;
	public DocPendingAppointmentsDTO(int id_doctor, int id_patient, String nombre, int edad, String genero) {
		super();
		this.id_doctor = id_doctor;
		this.id_patient = id_patient;
		this.nombre = nombre;
		this.edad = edad;
		this.genero = genero;
	}
	public int getId_doctor() {
		return id_doctor;
	}
	public void setId_doctor(int id_doctor) {
		this.id_doctor = id_doctor;
	}
	public int getId_patient() {
		return id_patient;
	}
	public void setId_patient(int id_patient) {
		this.id_patient = id_patient;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
