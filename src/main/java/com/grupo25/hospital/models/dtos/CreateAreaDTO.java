package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAreaDTO {
	@NotBlank
	private String name;
		
	@NotNull
	private Long id_shift;

	public CreateAreaDTO() {
		super();
	}

	public CreateAreaDTO(@NotBlank String name, @NotNull Long id_shift) {
		super();
		this.name = name;
		this.id_shift = id_shift;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId_shift() {
		return id_shift;
	}

	public void setId_shift(Long id_shift) {
		this.id_shift = id_shift;
	}
	
}
