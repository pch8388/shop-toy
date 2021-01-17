package me.study.shop.member.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.study.shop.common.ApiResult;
import me.study.shop.member.domain.User;
import me.study.shop.member.dto.RegisterResponseDto;
import me.study.shop.member.dto.UserRequestDto;
import me.study.shop.member.service.UserService;
import me.study.shop.security.Jwt;
import me.study.shop.security.JwtUtil;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = {"2. User"})
@RestController
@RequiredArgsConstructor
public class UserApi {

	private final Jwt jwt;
	private final UserService userService;

	@ApiOperation(value = "유저 등록", notes = "유저를 등록한다")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/users")
	public ApiResult<RegisterResponseDto> register(@RequestBody @Valid UserRequestDto dto) {
		final User user = userService.register(
			dto.getUsername(), dto.getPassword(),
			dto.getEmail(), dto.getAddress());

		final String token = JwtUtil.newApiToken(jwt, user);
		return new ApiResult<>(
			new RegisterResponseDto(token, user.getUsername(), user.getEmailAddress()));
	}
}
