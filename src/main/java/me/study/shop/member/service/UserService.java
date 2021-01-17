package me.study.shop.member.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;
import me.study.shop.member.exception.ExistEmailException;
import me.study.shop.member.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public User register(String username, String password, Email email, Address address) {
		userRepository.findByEmail(email)
			.ifPresent(e -> {
				throw new ExistEmailException();
			});

		final User user =
			User.createUser(username, passwordEncoder.encode(password), email, address);
		return userRepository.save(user);
	}
}
