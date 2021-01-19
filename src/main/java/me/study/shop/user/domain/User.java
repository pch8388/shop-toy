package me.study.shop.user.domain;

import static com.google.common.base.Preconditions.*;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.study.shop.common.domain.BaseEntity;

@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = {"id", "username", "email"})
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "user_id")
	private Long id;

	@Column(name = "username", length = 10, nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Embedded
	private Email email;

	@Embedded
	private Address address;

	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles;

	private LocalDateTime lastLoginAt;

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

	public String getEmailAddress() {
		return email.getEmailAddress();
	}

	public void login(PasswordEncoder passwordEncoder, String credential) {
		if (!passwordEncoder.matches(password, credential)) {
			throw new BadCredentialsException("Bad Credential");
		}

		this.lastLoginAt = LocalDateTime.now();
	}
}
