package me.study.shop.security;

import lombok.RequiredArgsConstructor;
import me.study.shop.user.domain.Email;

@RequiredArgsConstructor
public class JwtAuthentication {
	private final Long id;
	private final String username;
	private final Email email;
}
