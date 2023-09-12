package com.grupo25.hospital.models.dtos;

public class AddPrescriptionDTO {
	private Long id_drug;
	private String indication;
	private float daily_amount;
	private int quantity;
	private Long id_appoinment;
	
	public AddPrescriptionDTO() {
		super();
	}
	
	public AddPrescriptionDTO(Long id_drug, String indication, float daily_amount, int quantity, Long id_appoinment) {
		super();
		this.id_drug = id_drug;
		this.indication = indication;
		this.daily_amount = daily_amount;
		this.quantity = quantity;
		this.id_appoinment = id_appoinment;
	}
	public Long getId_drug() {
		return id_drug;
	}
	public void setId_drug(Long id_drug) {
		this.id_drug = id_drug;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
	public float getDaily_amount() {
		return daily_amount;
	}
	public void setDaily_amount(float daily_amount) {
		this.daily_amount = daily_amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Long getId_appoinment() {
		return id_appoinment;
	}
	public void setId_appoinment(Long id_appoinment) {
		this.id_appoinment = id_appoinment;
	}
	
	

}
