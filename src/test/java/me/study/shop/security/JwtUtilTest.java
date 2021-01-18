package me.study.shop.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import me.study.shop.user.domain.Role;
import me.study.shop.user.domain.User;

class JwtUtilTest {

	private Jwt jwt;

	@BeforeEach
	void setUp() {
		final String issuer = "test-issuer";
		final String clientSecret = "secret1234567890";
		final int expirySeconds = 10;

		jwt = new Jwt(issuer, clientSecret, expirySeconds);
	}

	@Test
	@DisplayName("api token 생성")
	void newApiToken() {
		User mockUser = mock(User.class);

		when(mockUser.getId()).thenReturn(1L);
		when(mockUser.getUsername()).thenReturn("tester");
		when(mockUser.getRoles()).thenReturn(Set.of(Role.ROLE_USER));

		final String newApiToken = JwtUtil.newApiToken(jwt, mockUser);
		assertNotNull(newApiToken);
	}
}