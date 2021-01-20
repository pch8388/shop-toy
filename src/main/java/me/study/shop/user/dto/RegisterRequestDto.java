package me.study.shop.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import me.study.shop.user.domain.Address;
import me.study.shop.user.domain.Email;

@Getter
public class RegisterRequestDto {
	@NotEmpty
	@Size(max = 20)
	@ApiModelProperty(value = "유저 이름", required = true)
	private String username;
	@NotEmpty
	@Size(max = 20)
	@ApiModelProperty(value = "비밀번호", required = true)
	private String password;
	@ApiModelProperty(value = "이메일 주소", required = true)
	private String emailAddress;
	@ApiModelProperty(value = "도시")
	private String city;
	@ApiModelProperty(value = "상세주소")
	private String street;
	@ApiModelProperty(value = "우편번호")
	private String zipcode;
}
