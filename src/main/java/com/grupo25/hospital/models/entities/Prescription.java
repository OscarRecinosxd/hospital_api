package com.grupo25.hospital.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name = "prescription")
public class Prescription {
	
	@Id
	@Column(name = "id_prescription")
	@SequenceGenerator(name = "prescription_id_prescription_gen", sequenceName = "prescription_id_prescription_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prescription_id_prescription_gen")
	private Long id_prescription;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_appointment")
	private Appointment id_appointment;
	
	@Column(name = "indication")
	private String indication;
	
	@Column(name = "daily_amount")
	private Float daily_amount;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_drug")
	private Drug id_drug;
	
	
	public Prescription() {
		super();
	}


	public Prescription(Appointment id_appointment, String indication, Float daily_amount, Integer quantity,
			Drug id_drug) {
		super();
		this.id_appointment = id_appointment;
		this.indication = indication;
		this.daily_amount = daily_amount;
		this.quantity = quantity;
		this.id_drug = id_drug;
	}


	public Long getId_prescription() {
		return id_prescription;
	}


	public void setId_prescription(Long id_prescription) {
		this.id_prescription = id_prescription;
	}


	public Appointment getId_appointment() {
		return id_appointment;
	}


	public void setId_appointment(Appointment id_appointment) {
		this.id_appointment = id_appointment;
	}


	public String getIndication() {
		return indication;
	}


	public void setIndication(String indication) {
		this.indication = indication;
	}


	public Float getDaily_amount() {
		return daily_amount;
	}


	public void setDaily_amount(Float daily_amount) {
		this.daily_amount = daily_amount;
	}


	public Integer getQuantity() {
		return quantity;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Drug getId_drug() {
		return id_drug;
	}


	public void setId_drug(Drug id_drug) {
		this.id_drug = id_drug;
	}
	
	
}
