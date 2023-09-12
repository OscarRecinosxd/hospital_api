package com.grupo25.hospital.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "drug")
public class Drug {
	
	@Id
	@Column(name = "id_drug")
	@SequenceGenerator(name = "drug_id_drug_gen", sequenceName = "drug_id_drug_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "drug_id_drug_gen")
	private Long id_drug;
	
	@Column(name = "drug_lab")
	private String drug_lab;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "active")
	private String active;
	
	@Column(name = "active_percentage")
	private Float active_percentage;

	public Drug() {
		super();
	}

	public Drug(Long id_drug, String drug_lab, String name, String active, Float active_percentage) {
		super();
		this.id_drug = id_drug;
		this.drug_lab = drug_lab;
		this.name = name;
		this.active = active;
		this.active_percentage = active_percentage;
	}

	public Long getId_drug() {
		return id_drug;
	}

	public void setId_drug(Long id_drug) {
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

	public Float getActive_percentage() {
		return active_percentage;
	}

	public void setActive_percentage(Float active_percentage) {
		this.active_percentage = active_percentage;
	}
	
	
}
