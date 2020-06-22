package me.study.shop.dto;

import lombok.Getter;
import me.study.shop.domain.Address;
import me.study.shop.domain.Member;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberRequestDto {
	@NotEmpty
	private String username;
	private Address address;

	public Member toEntity() {
		return Member.createMember(username, address);
	}
}
