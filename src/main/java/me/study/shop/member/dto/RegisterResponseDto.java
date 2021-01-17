package me.study.shop.member.dto;

import lombok.Getter;

@Getter
public class RegisterResponseDto {
	private String token;
	private String username;
	private String emailAddress;

	public RegisterResponseDto(String token, String username, String emailAddress) {
		this.token = token;
		this.username = username;
		this.emailAddress = emailAddress;
	}
}
