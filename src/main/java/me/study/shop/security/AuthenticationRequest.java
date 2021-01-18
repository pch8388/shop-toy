package me.study.shop.security;

import lombok.Getter;

@Getter
public class AuthenticationRequest {

	private final String principal;
	private final String credential;

	public AuthenticationRequest(String principal, String credential) {
		this.principal = principal;
		this.credential = credential;
	}
}
