package me.study.shop.config;

import static org.springframework.security.core.authority.AuthorityUtils.*;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import me.study.shop.security.JwtAuthentication;
import me.study.shop.security.JwtAuthenticationToken;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.Role;

public class WithMockCustomUserSecurityContextFactory
	implements WithSecurityContextFactory<WithMockCustomUser> {
	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
		final SecurityContext context = SecurityContextHolder.createEmptyContext();
		final Authentication auth = new JwtAuthenticationToken(
			new JwtAuthentication(1L, "tester", new Email("test00@gmail.com")),
			null,
			createAuthorityList(Role.ROLE_USER.name())
		);

		context.setAuthentication(auth);
		return context;
	}
}
