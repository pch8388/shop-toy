package me.study.shop.member.repository;

import java.util.Optional;

import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(Email email);
}
