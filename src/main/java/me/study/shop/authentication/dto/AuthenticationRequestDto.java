package me.study.shop.authentication.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
	@ApiModelProperty(value = "로그인 아이디")
	private String principal;
	@ApiModelProperty(value = "패스워드")
	private String credential;
}
