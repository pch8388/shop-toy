package me.study.shop.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.study.shop.member.dto.MemberRequestDto;
import me.study.shop.member.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"2. Member"})
@RestController
@RequiredArgsConstructor
public class MemberApi {

	private final MemberService memberService;

	@ApiOperation(value = "유저 등록", notes = "유저를 등록한다")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/member")
	public Long register(@RequestBody @Valid MemberRequestDto dto) {
		return memberService.register(dto.toEntity()).getId();
	}
}
