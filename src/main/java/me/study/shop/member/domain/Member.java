package me.study.shop.member.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String username;

	private String password;

	@Embedded
	private Address address;

	private Member(String username, String password, Address address) {
		validateUser(username, password);
		this.username = username;
		this.password = password;
		this.address = address;
	}

	private void validateUser(String username, String password) {

	}

	public static Member createMember(String username, String password, Address address) {
		return new Member(username, password, address);
	}
}
