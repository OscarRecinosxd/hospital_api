package com.grupo25.hospital.models.dtos;

public class GetEntityDTO {
	private int code;

	public GetEntityDTO(int code) {
		super();
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
