package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotNull;

public class EditAreaDTO {
	@NotNull
	private Long id;
	
	private String name;
	
	private Long id_shift;
	
	public EditAreaDTO() {
		super();
	}
	
	public EditAreaDTO(@NotNull Long id, String name, Long id_shift) {
		super();
		this.id = id;
		this.name = name;
		this.id_shift = id_shift;
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

	public Long getId_shift() {
		return id_shift;
	}

	public void setId_shift(Long id_shift) {
		this.id_shift = id_shift;
	}
	
}
