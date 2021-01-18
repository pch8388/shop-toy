package me.study.shop.user.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.user.domain.Address;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;
import me.study.shop.user.exception.ExistEmailException;
import me.study.shop.user.exception.NotFoundEmailException;
import me.study.shop.user.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

	@Transactional
	public User login(Email email, String password) {
		final User user = userRepository.findByEmail(email)
			.orElseThrow(NotFoundEmailException::new);
		user.login(passwordEncoder, password);
		return user;
	}
}
