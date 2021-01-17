package me.study.shop.member.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UserTest {

	@Test
	@DisplayName("유저 정상 생성")
	void createUser() {
		final String username = "test-user";
		final String password = "1234";
		final Email email = new Email("test@test.com");
		final Address address = new Address("Seoul", "street", "12345");
		final User newUser = User.createUser(username, password, email, address);

		assertNull(newUser.getId());
		assertEquals(username, newUser.getUsername());
		assertEquals(password, newUser.getPassword());
		assertEquals(email, newUser.getEmail());
		assertEquals(address, newUser.getAddress());
	}

	@ParameterizedTest
	@MethodSource("provideUser")
	@DisplayName("필수 값이 없으면 예외 발생")
	void createUser_exception(String username, String password, Email email, Address address) {
		assertThrows(IllegalArgumentException.class,
			() -> User.createUser(username, password, email, address));
	}

	private static Stream<Arguments> provideUser() {
		final String username = "test-user";
		final String password = "1234";
		final Email email = new Email("test@test.com");
		final Address address = new Address("Seoul", "street", "12345");

		return Stream.of(
			Arguments.of("", password, email, address),
			Arguments.of(username, "", email, address),
			Arguments.of(username, password, null, address),
			Arguments.of(username + username, password, email, address)
		);
	}
}