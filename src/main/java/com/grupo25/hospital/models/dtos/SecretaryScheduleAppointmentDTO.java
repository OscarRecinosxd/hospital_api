package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SecretaryScheduleAppointmentDTO {
	@NotBlank
	private String username;
	
	@NotNull
	private Long type;
	
	@NotNull
	private Long idVAT; //vaccine, area or test
	
	@NotBlank
	private String date;

	public SecretaryScheduleAppointmentDTO() {
		super();
	}

	public SecretaryScheduleAppointmentDTO(@NotBlank String username, @NotNull Long type, @NotNull Long idVAT,
			@NotBlank String date) {
		super();
		this.username = username;
		this.type = type;
		this.idVAT = idVAT;
		this.date = date;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getIdVAT() {
		return idVAT;
	}

	public void setIdVAT(Long idVAT) {
		this.idVAT = idVAT;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
