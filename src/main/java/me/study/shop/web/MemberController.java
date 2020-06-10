package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.MemberRequestDto;
import me.study.shop.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/member")
	public Long register(@RequestBody @Valid MemberRequestDto dto) {
		return memberService.register(dto.toEntity()).getId();
	}
}
