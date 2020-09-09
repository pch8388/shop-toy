package me.study.shop.member.dto;

import lombok.Getter;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Member;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberRequestDto {
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	private Address address;

	public Member toEntity() {
		return Member.createMember(username, password, address);
	}
}
