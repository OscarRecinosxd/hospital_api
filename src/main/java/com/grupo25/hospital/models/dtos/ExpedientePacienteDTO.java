package com.grupo25.hospital.models.dtos;

import java.time.LocalDate;

public class ExpedientePacienteDTO {
	private int code;
	private LocalDate date;
	private String tipo;
	private String cita_detalles;
	public ExpedientePacienteDTO(int code, LocalDate date, String tipo, String cita_detalles) {
		super();
		this.code = code;
		this.date = date;
		this.tipo = tipo;
		this.cita_detalles = cita_detalles;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCita_detalles() {
		return cita_detalles;
	}
	public void setCita_detalles(String cita_detalles) {
		this.cita_detalles = cita_detalles;
	}
}
