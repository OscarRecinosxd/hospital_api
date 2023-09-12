package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

import com.grupo25.hospital.models.entities.Area;
import com.grupo25.hospital.models.entities.Role;

public class PeopleResponseDTO {
	private Long id_person;
	private String name;
	private String username;
	private String last_name;
	private String email;
	private Character gender;
	private Boolean status;
	private LocalDate birthdate;
	private Role role;
	private Area area;
	
	public PeopleResponseDTO() {
		super();
	}

	public PeopleResponseDTO(Long id_person, String name, String username, String last_name, String email,
			Character gender, Boolean status, LocalDate birthdate, Role role, Area area) {
		super();
		this.id_person = id_person;
		this.name = name;
		this.username = username;
		this.last_name = last_name;
		this.email = email;
		this.gender = gender;
		this.status = status;
		this.birthdate = birthdate;
		this.role = role;
		this.area = area;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	
}
