package com.grupo25.hospital.models.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PrescriptionDTO {
	@NotNull
	private Long id_appointment;
	
	@NotNull
	private Long medicine;
	
	@NotNull
	private Float doses;
	
	@Min(1)
	private Integer quantity;
	
	private String indication;

	public PrescriptionDTO() {
		super();
	}

	public PrescriptionDTO(@NotNull Long id_appointment,@NotNull Long medicine, @NotNull Float doses, @Min(1) Integer quantity, String indication) {
		super();
		this.id_appointment = id_appointment;
		this.medicine = medicine;
		this.doses = doses;
		this.quantity = quantity;
		this.indication = indication;
	}

	public Long getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}

	public Long getMedicine() {
		return medicine;
	}

	public void setMedicine(Long medicine) {
		this.medicine = medicine;
	}

	public Float getDoses() {
		return doses;
	}

	public void setDoses(Float doses) {
		this.doses = doses;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public String getIndication() {
		return this.indication;
	}
	
	public void setIndication(String indication) {
		this.indication = indication;
	}

	
}