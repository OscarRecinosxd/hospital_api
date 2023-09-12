package com.grupo25.hospital.models.entities;

import java.time.LocalDate;
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

@Entity(name = "person")
public class Person {

	@Id
	@Column(name = "id_person")
	@SequenceGenerator(name = "person_id_person_gen", sequenceName = "person_id_person_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_id_person_gen")
	private Long id_person;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "last_name")
	private String last_name;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@Column(name = "gender")
	private Character gender;
	
	@Column(name = "birthdate")
	private LocalDate birthdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol")
	private Role id_role;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_area", nullable = true)
	private Area id_area;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JsonIgnore
	private List<Token> tokens;
	
	@OneToMany(mappedBy = "id_patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JsonIgnore
	private List<Appointment> appointmentsPatient;
	
	@OneToMany(mappedBy = "id_doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	@JsonIgnore
	private List<Appointment> appointmentsDoctor;

	public Person() {
		super();
	}

	public Person(String name,String username, String last_name, Boolean status, String email, String password,
			Character gender, LocalDate birthdate, Role id_role, Area id_area) {
		super();
		this.name = name;
		this.username = username;
		this.last_name = last_name;
		this.status = status;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthdate = birthdate;
		this.id_role = id_role;
		this.id_area = id_area;
	}

	public Long getId_person() {
		return id_person;
	}

	public void setId_person(Long id_person) {
		this.id_person = id_person;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Role getId_role() {
		return id_role;
	}

	public void setId_role(Role id_role) {
		this.id_role = id_role;
	}

	public Area getId_area() {
		return id_area;
	}

	public void setId_area(Area id_area) {
		this.id_area = id_area;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}
	
	
}
