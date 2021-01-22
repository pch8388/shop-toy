package me.study.shop.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class JwtAuthentication {
	private final Long id;
	private final String emailAddress;
	private final String username;
}
