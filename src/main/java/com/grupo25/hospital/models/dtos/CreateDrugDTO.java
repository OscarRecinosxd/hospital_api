package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CreateDrugDTO {
	private String drug_lab;
	
	private String name;
	
	@NotBlank
	private String active;
	
	@NotNull
	@Positive
	@Max(value = 100)
	private Float active_percentage;

	public CreateDrugDTO() {
		super();
	}

	

	public CreateDrugDTO(String drug_lab, String name, @NotBlank String active,
			@NotNull @Positive @Max(100) Float active_percentage) {
		super();
		this.drug_lab = drug_lab;
		this.name = name;
		this.active = active;
		this.active_percentage = active_percentage;
	}

	public String getDrug_lab() {
		return drug_lab;
	}

	public void setDrug_lab(String drug_lab) {
		this.drug_lab = drug_lab;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Float getActive_percentage() {
		return active_percentage;
	}

	public void setActive_percentage(Float active_percentage) {
		this.active_percentage = active_percentage;
	}
	
	
}
