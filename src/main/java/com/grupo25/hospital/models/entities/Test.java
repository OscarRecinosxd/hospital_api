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

@Entity(name="test")
public class Test {
	
	@Id
	@Column(name = "id_test")
	@SequenceGenerator(name = "test_id_test_gen", sequenceName = "test_id_test_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_id_test_gen")
	private Long id_test;
	
	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private Character gender;
	
	@Column(name="start_age")
	private Integer start_age;
	
	@Column(name="frequency")
	private Integer frequency;
	
	@OneToMany(mappedBy = "id_test", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Appointment> appointments;

	
	
	public Test() {
		super();
	}

	public Test(String name, Character gender, Integer start_age, Integer frequency) {
		super();
		this.name = name;
		this.gender = gender;
		this.start_age = start_age;
		this.frequency = frequency;
	}

	public Long getId_test() {
		return id_test;
	}

	public void setId_test(Long id_test) {
		this.id_test = id_test;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Integer getStart_age() {
		return start_age;
	}

	public void setStart_age(Integer start_age) {
		this.start_age = start_age;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}
	
	
}
