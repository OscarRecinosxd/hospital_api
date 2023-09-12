package com.grupo25.hospital.models.dtos;


import javax.validation.constraints.NotNull;

public class CreateInmunizationDTO {
	@NotNull
	private Long id_vaccine;
	
	@NotNull
	private Integer age;
	

	public CreateInmunizationDTO() {
		super();
	}

	public CreateInmunizationDTO(@NotNull Long id_vaccine, @NotNull Integer age) {
		super();
		this.id_vaccine = id_vaccine;
		this.age = age;
	}

	public Long getId_vaccine() {
		return id_vaccine;
	}

	public void setId_vaccine(Long id_vaccine) {
		this.id_vaccine = id_vaccine;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
