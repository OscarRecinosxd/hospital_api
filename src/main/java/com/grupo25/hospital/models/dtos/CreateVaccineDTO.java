package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateVaccineDTO {
	@NotBlank
	private String name;
	
	@NotNull
	private Integer required_doses;

	public CreateVaccineDTO() {
		super();
	}

	public CreateVaccineDTO(@NotBlank String name, @NotNull Integer required_doses) {
		super();
		this.name = name;
		this.required_doses = required_doses;
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
