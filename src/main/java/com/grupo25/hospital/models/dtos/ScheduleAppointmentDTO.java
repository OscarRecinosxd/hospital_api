package com.grupo25.hospital.models.dtos;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ScheduleAppointmentDTO {
	@NotNull
	private Long type;
	
	@NotNull
	private Long idVAT; //vaccine, area or test
	
	@NotBlank
	private String date;

	public ScheduleAppointmentDTO() {
		super();
	}

	public ScheduleAppointmentDTO(@NotNull Long type,@NotNull Long idVAT,@NotBlank String date) {
		super();
		this.type = type;
		this.idVAT = idVAT;
		this.date = date;
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
