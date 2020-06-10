package me.study.shop.domain;

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

	@Embedded
	private Address address;

	private Member(String username, Address address) {
		this.username = username;
		this.address = address;
	}

	public static Member createMember(String username, Address address) {
		return new Member(username, address);
	}
}
