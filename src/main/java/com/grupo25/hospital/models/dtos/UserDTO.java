package com.grupo25.hospital.models.dtos;

public class UserDTO {
	private int code;
	private String name;
	private String last_name;
	private String email;
	private String password;
	private int rol;
	private boolean status;
	public UserDTO( int code, String name, String last_name, String email, String password, int rol,
			boolean status) {
		super();
		this.code = code;
		this.name = name;
		this.last_name = last_name;
		this.email = email;
		this.password = password;
		this.rol = rol;
		this.status = status;
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
	public int getRol() {
		return rol;
	}
	public void setRol(int rol) {
		this.rol = rol;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
}
