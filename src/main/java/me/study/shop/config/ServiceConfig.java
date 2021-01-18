package me.study.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.study.shop.security.Jwt;

@Configuration
public class ServiceConfig {

	@Bean
	public Jwt jwt(JwtTokenConfig jwtTokenConfigure) {
		return new Jwt(jwtTokenConfigure.getIssuer(),
			jwtTokenConfigure.getClientSecret(),
			jwtTokenConfigure.getExpirySeconds());
	}
}
