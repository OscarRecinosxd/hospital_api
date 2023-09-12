package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

public class UserPrescriptionDTO {
	private String name;
	private LocalDate date;
	private int daily_amount;
	private int quantity;
	private String indication;
	public UserPrescriptionDTO(String name, LocalDate date, int daily_amount, int quantity, String indication) {
		super();
		this.name = name;
		this.date = date;
		this.daily_amount = daily_amount;
		this.quantity = quantity;
		this.indication = indication;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getDaily_amount() {
		return daily_amount;
	}
	public void setDaily_amount(int daily_amount) {
		this.daily_amount = daily_amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIndication() {
		return indication;
	}
	public void setIndication(String indication) {
		this.indication = indication;
	}
}
