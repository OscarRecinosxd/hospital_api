package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotNull;

public class EditInmunizationDTO {
	@NotNull
	private Long id_inmunization;
	
	private Integer age;
	
	private Character gender;

	public EditInmunizationDTO() {
		super();
	}

	public EditInmunizationDTO(@NotNull Long id_inmunization, Integer age, Character gender) {
		super();
		this.id_inmunization = id_inmunization;
		this.age = age;
		this.gender = gender;
	}

	public Long getId_inmunization() {
		return id_inmunization;
	}

	public void setId_inmunization(Long id_inmunization) {
		this.id_inmunization = id_inmunization;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
	
	
}
