package me.study.shop.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AuthenticationResult {
	private final String apiToken;
	private final String username;
	private final String emailAddress;
}
