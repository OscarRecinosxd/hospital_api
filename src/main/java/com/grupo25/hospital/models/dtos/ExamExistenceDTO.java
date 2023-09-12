package com.grupo25.hospital.models.dtos;

public class ExamExistenceDTO {
	private int code;
	private String name;
	private String gender;
	private int start_age;
	private int frequency;
	public ExamExistenceDTO(int code, String name, String gender, int start_age, int frequency) {
		super();
		this.code = code;
		this.name = name;
		this.gender = gender;
		this.start_age = start_age;
		this.frequency = frequency;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
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
