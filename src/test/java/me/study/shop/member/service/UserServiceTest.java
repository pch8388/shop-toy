package me.study.shop.member.service;

import me.study.shop.common.exception.ErrorCode;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;
import me.study.shop.member.exception.ExistEmailException;
import me.study.shop.member.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith({MockitoExtension.class})
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	private final String username = "member1";
	private final String password = "test";
	private final Email email = new Email("test@test.com");
	private final Address address = new Address("Seoul", "road", "12345");

	@Test
	@DisplayName("유저 등록")
	void register() {
		when(passwordEncoder.encode(any())).thenReturn("encoded-password");

		userService.register(username, password, email, address);

		verify(passwordEncoder).encode(password);
		verify(userRepository).save(any(User.class));
	}

	@Test
	@DisplayName("유저 등록시 - 이미 등록된 이메일이면 예외발생")
	void register_existEmail() {
		final User user = User.createUser(username, password, email, address);

		given(userRepository.findByEmail(any())).willReturn(Optional.of(user));

		assertThatThrownBy(() -> userService.register(username, password, email, address))
			.isInstanceOf(ExistEmailException.class)
			.hasMessage(ErrorCode.EXIST_EMAIL.getMessage());
	}
}