package com.grupo25.hospital.models.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "vaccine")
public class Vaccine {
	
	@Id
	@Column(name = "id_vaccine")
	@SequenceGenerator(name = "vaccine_id_vaccine_gen", sequenceName = "vaccine_id_vaccine_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vaccine_id_vaccine_gen")
	private Long id_vaccine;
	
	@Column(name="name")
	private String name;
	@Column(name= "required_doses")
	private Integer required_doses;
	
	@OneToMany(mappedBy = "id_vaccine", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Inmunization> inmunizations;
	
	@OneToMany(mappedBy = "id_vaccine", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Appointment> appointments;
	
	public Vaccine() {
		super();
	}

	public Vaccine(String name, Integer required_doses, Character gender) {
		super();
		this.name = name;
		this.required_doses = required_doses;
	}

	public Long getId_vaccine() {
		return id_vaccine;
	}

	public void setId_vaccine(Long id_vaccine) {
		this.id_vaccine = id_vaccine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRequired_doses() {
		return required_doses;
	}

	public void setRequired_doses(Integer required_doses) {
		this.required_doses = required_doses;
	}

	public List<Inmunization> getInmunizations() {
		return inmunizations;
	}

	public void setInmunizations(List<Inmunization> inmunizations) {
		this.inmunizations = inmunizations;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
	
}
