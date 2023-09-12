package com.grupo25.hospital.models.dtos;

public class PatientInConsultDTO {
	private int code;
	private String name;
	private String last_name;
	private int age;
	private String gender;
	public PatientInConsultDTO(int code, String name, String last_name, int age, String gender) {
		super();
		this.code = code;
		this.name = name;
		this.last_name = last_name;
		this.age = age;
		this.gender = gender;
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
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}
