package me.study.shop.member.service;

import java.util.Optional;

import lombok.RequiredArgsConstructor;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.Member;
import me.study.shop.member.exception.ExistEmailException;
import me.study.shop.member.repository.MemberRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	@Transactional
	public Member register(String username, String password, Email email, Address address) {
		memberRepository.findByEmail(email)
			.ifPresent(e -> {
				throw new ExistEmailException();
			});

		final Member member =
			Member.createMember(username, passwordEncoder.encode(password), email, address);
		return memberRepository.save(member);
	}
}
