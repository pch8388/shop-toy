package me.study.shop.member.repository;

import java.util.Optional;

import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(Email email);
}
