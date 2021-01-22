package me.study.shop.security;

import java.util.stream.Collectors;

import me.study.shop.user.domain.Role;
import me.study.shop.user.domain.User;

public class JwtUtil {
	public static String newApiToken(Jwt jwt, User user) {
		final String[] roles = user.getRoles()
			.stream()
			.map(Role::getAuthority)
			.collect(Collectors.toSet())
			.toArray(new String[user.getRoles().size()]);
		final Jwt.Claims claims = Jwt.Claims.of(user.getId(), user.getEmailAddress(), user.getUsername(), roles);
		return jwt.newToken(claims);
	}
}
