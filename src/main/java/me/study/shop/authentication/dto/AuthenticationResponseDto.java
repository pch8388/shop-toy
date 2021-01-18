package me.study.shop.authentication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import me.study.shop.security.AuthenticationResult;

@Getter
public class AuthenticationResponseDto {

	@ApiModelProperty(value = "api 토큰")
	private final String apiToken;
	@ApiModelProperty(value = "등록한 유저이름")
	private final String username;
	@ApiModelProperty(value = "등록한 이메일 주소")
	private final String emailAddress;

	public AuthenticationResponseDto(AuthenticationResult result) {
		this.apiToken = result.getApiToken();
		this.username = result.getUsername();
		this.emailAddress = result.getEmailAddress();
	}
}
