package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Member;
import me.study.shop.repository.MemberRepository;
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
