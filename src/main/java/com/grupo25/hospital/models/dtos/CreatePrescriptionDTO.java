package com.grupo25.hospital.models.dtos;

import java.util.List;

import javax.validation.constraints.NotNull;

public class CreatePrescriptionDTO {
	@NotNull
	private Long id_appointment;
	
	private List<PrescriptionDTO> medicines;
	
	private String indication;

	public CreatePrescriptionDTO() {
		super();
	}

	public CreatePrescriptionDTO(@NotNull Long id_appointment, List<PrescriptionDTO> medicines, String indication) {
		super();
		this.id_appointment = id_appointment;
		this.medicines = medicines;
		this.indication = indication;
	}

	public Long getId_appointment() {
		return id_appointment;
	}

	public void setId_appointment(Long id_appointment) {
		this.id_appointment = id_appointment;
	}

	public List<PrescriptionDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<PrescriptionDTO> medicines) {
		this.medicines = medicines;
	}

	public String getIndication() {
		return indication;
	}

	public void setIndication(String indication) {
		this.indication = indication;
	}
	
	
}