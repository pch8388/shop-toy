package me.study.shop.security;

import static java.util.Objects.*;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import lombok.extern.slf4j.Slf4j;
import me.study.shop.user.domain.Email;

@Slf4j
public class JwtAuthenticationTokenFilter extends GenericFilterBean {

	private static final Pattern BEARER = Pattern.compile("^Bearer$", Pattern.CASE_INSENSITIVE);
	private static final long REFRESH_RANGE_MILLS = 6000 * 10;
	
	private final String headerKey;
	private final Jwt jwt;
	
	public JwtAuthenticationTokenFilter(String headerKey, Jwt jwt) {
		this.headerKey = headerKey;
		this.jwt = jwt;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws
		IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			String authorizationToken = obtainAuthorizationToken(request);
			if (authorizationToken != null) {
				Jwt.Claims claims = jwt.verify(authorizationToken);

				
				if (canRefresh(claims)) {
					final String refreshToken = jwt.refreshToken(authorizationToken);
					response.setHeader(headerKey, refreshToken);
				}
				
				Long userKey = claims.userKey;
				String emailAddress = claims.emailAddress;
				String username = claims.name;

				List<GrantedAuthority> authorities = obtainAuthorities(claims);

				if (validAuthentication(emailAddress, username, authorities)) {
					final JwtAuthenticationToken authentication =
						new JwtAuthenticationToken(
							new JwtAuthentication(userKey, emailAddress, username), null, authorities);
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authentication);
				}
			}
		} else {
			SecurityContextHolder.getContext().getAuthentication();
		}

		chain.doFilter(request, response);
	}

	private boolean validAuthentication(String emailAddress, String username, List<GrantedAuthority> authorities) {
		return nonNull(emailAddress) && !username.isEmpty() && authorities.size() > 0;
	}

	private List<GrantedAuthority> obtainAuthorities(Jwt.Claims claims) {
		String[] roles = claims.roles;
		return roles == null || roles.length == 0 ?
			Collections.emptyList() :
			Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(toList());
	}

	private boolean canRefresh(Jwt.Claims claims) {
		long exp = claims.exp();
		if (exp > 0) {
			long remain = exp - System.currentTimeMillis();
			return remain < REFRESH_RANGE_MILLS;
		}
		return false;
	}

	private String obtainAuthorizationToken(HttpServletRequest request) {
		String token = request.getHeader(headerKey);
		if (token != null) {
			token = URLDecoder.decode(token, StandardCharsets.UTF_8);
			final String[] parts = token.split(" ");
			if (parts.length == 2) {
				String scheme = parts[0];
				String credentials = parts[1];
				return BEARER.matcher(scheme).matches() ? credentials : null;
			}
		}
		return null;
	}
}
