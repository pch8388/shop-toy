package me.study.shop.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class RegisterResponseDto {

	@ApiModelProperty(value = "api 토큰")
	private final String apiToken;
	@ApiModelProperty(value = "등록한 유저이름")
	private final String username;
	@ApiModelProperty(value = "등록한 이메일 주소")
	private final String emailAddress;

	public RegisterResponseDto(String apiToken, String username, String emailAddress) {
		this.apiToken = apiToken;
		this.username = username;
		this.emailAddress = emailAddress;
	}
}
