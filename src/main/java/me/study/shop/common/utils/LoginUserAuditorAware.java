package me.study.shop.common.utils;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import me.study.shop.security.JwtAuthentication;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.ofNullable(SecurityContextHolder.getContext())
			.map(SecurityContext::getAuthentication)
			.filter(this::authenticationPredicate)
			.map(Authentication::getPrincipal)
			.map(JwtAuthentication.class::cast)
			.map(JwtAuthentication::getUsername);
	}

	private boolean authenticationPredicate(Authentication auth) {
		return auth.isAuthenticated()
			&& auth.getAuthorities().stream()
				.noneMatch(g -> "ROLE_ANONYMOUS".equalsIgnoreCase(g.getAuthority()));
	}
}
