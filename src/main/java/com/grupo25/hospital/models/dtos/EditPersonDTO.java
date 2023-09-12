package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class EditPersonDTO {
	@NotNull
	private Long id;
	
	//@Pattern(regexp = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")
	@Email
	private String email;
	
	private Boolean status;

	public EditPersonDTO() {
		super();
	}

	public EditPersonDTO(@NotNull Long id, @Email String email, Boolean status) {
		super();
		this.id = id;
		this.email = email;
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
	
}
