package com.grupo25.hospital.models.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "appointment")
public class Appointment {
	
	@Id
	@Column(name = "id_appointment")
	@SequenceGenerator(name = "appointment_id_appointment_gen", sequenceName = "appointment_id_appointment_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_id_appointment_gen")
	private Long id_appointment;
	
	//paciente puede tener varias citas, pero una cita puede tener solo un paciente
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_patient")
	private Person id_patient;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_doctor", nullable = true)
	private Person id_doctor;
	
	@Column(name = "appointment_time")
	private LocalDateTime timestamp;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "appointment_details", nullable = true)
	private String appointment_details;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_appointment_type")
	private Appointment_type id_appointment_type;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_inmunization", nullable = true)
	private Inmunization id_inmunization;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_area")
	private Area id_area;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_test", nullable = true)
	private Test id_test;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_vaccine", nullable = true) 
	private Vaccine id_vaccine;
	
	
	@OneToMany(mappedBy = "id_appointment", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Appointment> appointments;


	public Appointment(Person id_patient, Person id_doctor, LocalDateTime timestamp, Boolean status,
			String appointment_details, Appointment_type id_appointment_type, Inmunization id_inmunization,
			Area id_area, Test id_test,Vaccine id_vaccine ) {
		super();
		this.id_patient = id_patient;
		this.id_doctor = id_doctor;
		this.timestamp = timestamp;
		this.status = status;
		this.appointment_details = appointment_details;
		this.id_appointment_type = id_appointment_type;
		this.id_inmunization = id_inmunization;
		this.id_area = id_area;
		this.id_test = id_test;
		this.id_vaccine = id_vaccine;
	}

	

	public Appointment() {
		super();
	}



	public Long getId_appointment() {
		return id_appointment;
	}


	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}


	public Person getId_patient() {
		return id_patient;
	}


	public void setId_patient(Person id_patient) {
		this.id_patient = id_patient;
	}


	public Person getId_doctor() {
		return id_doctor;
	}


	public void setId_doctor(Person id_doctor) {
		this.id_doctor = id_doctor;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public String getAppointment_details() {
		return appointment_details;
	}


	public void setAppointment_details(String appointment_details) {
		this.appointment_details = appointment_details;
	}


	public Appointment_type getId_appointment_type() {
		return id_appointment_type;
	}


	public void setId_appointment_type(Appointment_type id_appointment_type) {
		this.id_appointment_type = id_appointment_type;
	}


	public Inmunization getId_inmunization() {
		return id_inmunization;
	}


	public void setId_inmunization(Inmunization id_inmunization) {
		this.id_inmunization = id_inmunization;
	}


	public Test getId_test() {
		return id_test;
	}


	public void setId_test(Test id_test) {
		this.id_test = id_test;
	}



	public Area getId_area() {
		return id_area;
	}



	public void setId_area(Area id_area) {
		this.id_area = id_area;
	}
	



	
	 public Vaccine getId_vaccine() { return id_vaccine; }
	  
	  
	  
	  public void setId_vaccine(Vaccine id_vaccine) { this.id_vaccine = id_vaccine;
	 }
	 



	public List<Appointment> getAppointments() {
		return appointments;
	}



	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
}
