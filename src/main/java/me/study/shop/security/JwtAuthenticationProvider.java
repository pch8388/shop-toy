package me.study.shop.security;

import static org.springframework.security.core.authority.AuthorityUtils.*;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.Role;
import me.study.shop.user.domain.User;
import me.study.shop.user.service.UserService;

public class JwtAuthenticationProvider implements AuthenticationProvider {

	private final Jwt jwt;
	private final UserService userService;

	public JwtAuthenticationProvider(Jwt jwt, UserService userService) {
		this.jwt = jwt;
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		JwtAuthenticationToken token = (JwtAuthenticationToken)authentication;
		return processAuthentication(token.getAuthenticationRequest());
	}

	private Authentication processAuthentication(AuthenticationRequest request) {
		final User user = userService.login(new Email(request.getPrincipal()), request.getCredential());
		final JwtAuthenticationToken authenticated = new JwtAuthenticationToken(
			new JwtAuthentication(user.getId(), user.getUsername(), user.getEmail()),
			null,
			createAuthorityList(Role.ROLE_USER.name()));
		final String apiToken = JwtUtil.newApiToken(jwt, user);
		authenticated.setDetails(new AuthenticationResult(apiToken, user.getUsername(), user.getEmailAddress()));
		return authenticated;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(JwtAuthenticationToken.class);
	}
}
