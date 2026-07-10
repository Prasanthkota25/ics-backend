package com.example.bizx.ics.dto;

public class LoginResponseDTO {

	private String message;
	private Integer userId;
	private String username;

	public LoginResponseDTO() {
	}

	public LoginResponseDTO(String message) {
		this.message = message;
	}

	public LoginResponseDTO(String message, Integer userId, String username) {
		this.message = message;
		this.userId = userId;
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
