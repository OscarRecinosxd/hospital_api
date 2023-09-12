package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotNull;

public class EditVaccineDTO {
	@NotNull
	private Long id;
	private String name;
	private Integer required_doses;
	public EditVaccineDTO() {
		super();
	}
	public EditVaccineDTO(@NotNull Long id, String name, Integer required_doses) {
		super();
		this.id = id;
		this.name = name;
		this.required_doses = required_doses;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
