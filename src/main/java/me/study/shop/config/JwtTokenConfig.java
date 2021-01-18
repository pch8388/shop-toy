package me.study.shop.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "jwt.token")
@Getter
@Setter
public class JwtTokenConfig {
	private String header;
	private String issuer;
	private String clientSecret;
	private int expirySeconds;
}
