package me.study.shop.authentication.dto;

import javax.validation.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
	@NotEmpty
	@ApiModelProperty(value = "로그인 아이디 : 이메일", required = true)
	private String principal;
	@NotEmpty
	@ApiModelProperty(value = "패스워드", required = true)
	private String credential;
}
