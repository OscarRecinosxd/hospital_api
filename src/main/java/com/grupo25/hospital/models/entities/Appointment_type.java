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

@Entity(name="appointment_type")
public class Appointment_type {
	
	@Id
	@Column(name = "id_appointment_type")
	@SequenceGenerator(name = "appointment_type_id_appointment_type_gen", sequenceName = "appointment_type_id_appointment_type_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_type_id_appointment_type_gen")
	private Long id_appointment_type;
	
	@Column(name= "type_name")
	private String type_name;
	
	@OneToMany(mappedBy = "id_appointment_type", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Appointment> appointments;

	public Appointment_type( String type_name) {
		super();
		this.type_name = type_name;
	}

	public Appointment_type() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId_appointment_type() {
		return id_appointment_type;
	}

	public void setId_appointment_type(Long id_appointment_type) {
		this.id_appointment_type = id_appointment_type;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	
	
	
}
