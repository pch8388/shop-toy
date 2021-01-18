package me.study.shop.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import me.study.shop.user.domain.Address;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class RegisterRequestDto {
	@NotEmpty
	@Size(max = 20)
	@ApiModelProperty(value = "유저 이름")
	private String username;
	@NotEmpty
	@Size(max = 20)
	@ApiModelProperty(value = "비밀번호")
	private String password;
	@ApiModelProperty(value = "이메일 주소")
	private Email email;
	@ApiModelProperty(value = "주소")
	private Address address;

	public User toEntity() {
		return User.createUser(username, password, email, address);
	}
}
