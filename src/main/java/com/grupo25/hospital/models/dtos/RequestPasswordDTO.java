package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotBlank;

public class RequestPasswordDTO {
	@NotBlank
	private String username;

	public RequestPasswordDTO() {
		super();
	}

	public RequestPasswordDTO(@NotBlank String username) {
		super();
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	
}
