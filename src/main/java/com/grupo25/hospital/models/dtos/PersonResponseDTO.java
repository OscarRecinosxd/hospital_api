package com.grupo25.hospital.models.dtos;

public class PersonResponseDTO {
	private String email;
	private Boolean status;
	
	public PersonResponseDTO() {
		super();
	}
	
	

	public PersonResponseDTO(String email, Boolean status) {
		super();
		this.email = email;
		this.status = status;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
