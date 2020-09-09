package me.study.shop.member.service;

import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Member;
import me.study.shop.member.service.MemberService;
import me.study.shop.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	@Test
	@DisplayName("유저 등록")
	public void register() {
		final Member member = Member.createMember(
			"member1", "test", new Address("Seoul", "road", "12345"));

		given(memberRepository.save(any())).willReturn(member);

		memberService.register(member);

		verify(memberRepository).save(any(Member.class));
	}
}