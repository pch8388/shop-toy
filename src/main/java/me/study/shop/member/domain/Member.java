package me.study.shop.member.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	private Email email;

	@Embedded
	private Address address;

	private Member(String username, String password, Email email, Address address) {
		validateUser(username, password);

		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
	}

	private void validateUser(String username, String password) {
		Assert.notNull(username, "username must be provided");
		Assert.notNull(password, "password must be provided");
	}

	public static Member createMember(String username, String password, Email email, Address address) {
		return new Member(username, password, email, address);
	}
}
