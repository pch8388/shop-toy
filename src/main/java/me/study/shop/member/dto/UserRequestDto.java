package me.study.shop.member.dto;

import lombok.Getter;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class UserRequestDto {
	@NotEmpty
	@Size(max = 20)
	private String username;
	@NotEmpty
	@Size(max = 20)
	private String password;
	private Email email;
	private Address address;

	public User toEntity() {
		return User.createUser(username, password, email, address);
	}
}
