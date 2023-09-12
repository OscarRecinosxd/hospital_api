package com.grupo25.hospital.models.dtos;

public class AreaExistenceDTO {
	private int id_area;
	private String name;
	private String gender;
	private int start_age;
	private int frequency;
	
	public AreaExistenceDTO(int id_area, String name, String gender, int start_age, int frequency) {
		super();
		this.id_area = id_area;
		this.name = name;
		this.gender = gender;
		this.start_age = start_age;
		this.frequency = frequency;
	}

	public int getId_area() {
		return id_area;
	}

	public void setId_area(int id_area) {
		this.id_area = id_area;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getStart_age() {
		return start_age;
	}

	public void setStart_age(int start_age) {
		this.start_age = start_age;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	
	

}
