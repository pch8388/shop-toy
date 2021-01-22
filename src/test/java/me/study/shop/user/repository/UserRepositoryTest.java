package me.study.shop.user.repository;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import me.study.shop.config.WithMockCustomUser;
import me.study.shop.user.domain.Address;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;

@SpringBootTest
@Transactional
@WithMockCustomUser
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	private User saveUser;

	@BeforeEach
	void setUp() {
		final String username = "tester";
		final String password = "1234";
		final Email email = new Email("test@test.com");
		final Address address = new Address("Seoul", "street", "12345");

		saveUser = userRepository.save(
			User.createUser(username, password, email, address));
	}

	@Test
	@DisplayName("auditing 테스트")
	void baseTimeEntity() {
		LocalDateTime now = LocalDateTime.now();

		assertThat(saveUser.getCreateDate()).isBefore(now);
	}
}