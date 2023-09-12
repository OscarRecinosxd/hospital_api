package com.grupo25.hospital.models.dtos;

public class MessageDTO {
	private String message;

	public MessageDTO(String message) {
		super();
		this.message = message;
	}

	public MessageDTO() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
