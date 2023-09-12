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

@Entity(name = "role")
public class Role {

	@Id
	@Column(name = "id_rol")
	@SequenceGenerator(name = "role_id_rol_gen", sequenceName = "role_id_rol_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_id_rol_gen")
	private Long id_role;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "id_role", fetch = FetchType.LAZY, cascade = CascadeType.MERGE )
	@JsonIgnore
	private List<Person> people;
	
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Long getId_role() {
		return id_role;
	}

	public void setId_role(Long id_role) {
		this.id_role = id_role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	
	
}
