package com.grupo25.hospital.models.dtos;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreatePersonDTO {
	@NotBlank
	//@Size(min = 3)
	private String name;
	
	@NotBlank
	@Size(min = 4)
	private String username;
	
	@NotBlank
	@Size(min = 4)
	private String last_name;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Size(min=8, max=32)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
	private String password;
	
	@NotNull
	private Long role;
	
	@NotNull
	private Character gender;
	
	@NotBlank
	private String birthdate;
	
	//El área puede ser nula porque un paciente no pertenece a un área
	private Long area;

	public CreatePersonDTO() {
		super();
	}

	

	public CreatePersonDTO(@NotBlank String name, @NotBlank @Size(min = 4) String username,
			@NotBlank @Size(min = 4) String last_name, @NotBlank @Email String email,
			@NotBlank @Size(min = 8, max = 32) @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$") String password,
			@NotNull Long role, @NotNull Character gender, @NotBlank String birthdate, Long area) {
		super();
		this.name = name;
		this.username = username;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.gender = gender;
		this.birthdate = birthdate;
		this.area = area;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getRole() {
		return role;
	}

	public void setRole(Long role) {
		this.role = role;
	}

	public Character getGender() {
		return gender;
	}

	public void setGender(Character gender) {
		this.gender = gender;
	}
	

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}
	
	
	
}