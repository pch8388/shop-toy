package me.study.shop.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {

	private final Object principal;
	private String credential;

	public JwtAuthenticationToken(String principal, String credential) {
		super(null);
		super.setAuthenticated(false);

		this.principal = principal;
		this.credential = credential;
	}

	public JwtAuthenticationToken(Object principal, String credential,
		Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		super.setAuthenticated(true);

		this.principal = principal;
		this.credential = credential;
	}

	AuthenticationRequest getAuthenticationRequest() {
		return new AuthenticationRequest(String.valueOf(principal), credential);
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public String getCredentials() {
		return credential;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.credential = null;
	}
}
