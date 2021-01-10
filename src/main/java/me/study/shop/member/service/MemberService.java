package me.study.shop.member.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.member.domain.Member;
import me.study.shop.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Member register(Member member) {
		return memberRepository.save(member);
	}
}
