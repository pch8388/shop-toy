package me.study.shop.member.service;

import me.study.shop.common.exception.ErrorCode;
import me.study.shop.member.domain.Address;
import me.study.shop.member.domain.Email;
import me.study.shop.member.domain.Member;
import me.study.shop.member.exception.ExistEmailException;
import me.study.shop.member.service.MemberService;
import me.study.shop.member.repository.MemberRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

@ExtendWith({MockitoExtension.class})
class MemberServiceTest {

	@Mock
	private MemberRepository memberRepository;

	@InjectMocks
	private MemberService memberService;

	private final String username = "member1";
	private final String password = "test";
	private final Email email = new Email("test@test.com");
	private final Address address = new Address("Seoul", "road", "12345");

	@Test
	@DisplayName("유저 등록")
	void register() {
		final Member member = Member.createMember(username, password, email, address);

		given(memberRepository.save(any())).willReturn(member);

		memberService.register(username, password, email, address);

		verify(memberRepository).save(any(Member.class));
	}

	@Test
	@DisplayName("유저 등록시 - 이미 등록된 이메일이면 예외발생")
	void register_existEmail() {
		final Member member = Member.createMember(username, password, email, address);

		given(memberRepository.findByEmail(any())).willReturn(Optional.of(member));

		assertThatThrownBy(() -> memberService.register(username, password, email, address))
			.isInstanceOf(ExistEmailException.class)
			.hasMessage(ErrorCode.EXIST_EMAIL.getMessage());
	}
}