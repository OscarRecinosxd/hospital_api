package com.grupo25.hospital.models.dtos;

public class DrugsExistenceDTO {
	private int id_drug;
	private String drug_lab;
	private String name;
	private String active;
	private float active_percentage;
	public DrugsExistenceDTO(int id_drug, String drug_lab, String name, String active, float active_percentage) {
		super();
		this.id_drug = id_drug;
		this.drug_lab = drug_lab;
		this.name = name;
		this.active = active;
		this.active_percentage = active_percentage;
	}
	public int getId_drug() {
		return id_drug;
	}
	public void setId_drug(int id_drug) {
		this.id_drug = id_drug;
	}
	public String getDrug_lab() {
		return drug_lab;
	}
	public void setDrug_lab(String drug_lab) {
		this.drug_lab = drug_lab;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public float getActive_percentage() {
		return active_percentage;
	}
	public void setActive_percentage(float active_percentage) {
		this.active_percentage = active_percentage;
	}
}
