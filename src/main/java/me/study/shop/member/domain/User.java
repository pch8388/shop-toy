package me.study.shop.member.domain;

import static com.google.common.base.Preconditions.*;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.study.shop.security.Jwt;

@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id", "username", "email"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

	@Id @GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String username;

	private String password;

	@Embedded
	private Email email;

	@Embedded
	private Address address;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles;

	private User(String username, String password, Email email, Address address, Role... roles) {
		validateUser(username, password, email);

		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.roles = Set.of(roles);
	}

	private void validateUser(String username, String password, Email email) {
		checkArgument(!username.isBlank(), "username must be provided.");
		checkArgument(
			username.length() >= 1 && username.length() <= 10,
			"username length must be between 1 and 10 characters."
		);
		checkArgument(email != null, "email must be provided.");
		checkArgument(!password.isBlank(), "password must be provided.");
	}

	public static User createUser(
		String username, String password, Email email, Address address) {
		return new User(username, password, email, address, Role.ROLE_USER);
	}
}
