package com.grupo25.hospital.models.dtos;

import java.time.LocalDateTime;

public class ExpedienteDTO {
	private LocalDateTime fecha;
	private String tipo;
	private String detallesCita;
	
	
	public ExpedienteDTO() {
		super();
	}
	
	public ExpedienteDTO(LocalDateTime fecha, String tipo, String detallesCita) {
		super();
		this.fecha = fecha;
		this.tipo = tipo;
		this.detallesCita = detallesCita;
	}
	public LocalDateTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalDateTime localDateTime) {
		this.fecha = localDateTime;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDetallesCita() {
		return detallesCita;
	}
	public void setDetallesCita(String detallesCita) {
		this.detallesCita = detallesCita;
	}
}
