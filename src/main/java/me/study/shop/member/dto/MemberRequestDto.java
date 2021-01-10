package me.study.shop.member.dto;

import lombok.Getter;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.Member;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
public class MemberRequestDto {
	@NotEmpty
	@Size(max = 20)
	private String username;
	@NotEmpty
	@Size(max = 20)
	private String password;
	@javax.validation.constraints.Email
	private Email email;
	private Address address;

	public Member toEntity() {
		return Member.createMember(username, password, email, address);
	}
}
