package com.grupo25.hospital.models.dtos;

public class PrescriptionInfoDTO {
	private Float doses;
	private Integer quantity;
	private String indication;
	
	public PrescriptionInfoDTO() {
		super();
	}
	
	public PrescriptionInfoDTO(Float doses, Integer quantity, String indication) {
		super();
		this.doses = doses;
		this.quantity = quantity;
		this.indication = indication;
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
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}
	
	
}