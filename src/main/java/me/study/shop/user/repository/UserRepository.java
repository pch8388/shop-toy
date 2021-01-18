package me.study.shop.user.repository;

import java.util.Optional;

import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(Email email);
}
