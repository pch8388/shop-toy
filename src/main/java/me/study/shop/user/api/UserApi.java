package me.study.shop.user.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.study.shop.common.ApiResult;
import me.study.shop.user.domain.Address;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;
import me.study.shop.user.dto.RegisterResponseDto;
import me.study.shop.user.dto.RegisterRequestDto;
import me.study.shop.user.service.UserService;
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

	@PostMapping("/api/v1/users")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "유저 등록", notes = "유저를 등록한다")
	public ApiResult<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto) {
		final User user = userService.register(
			dto.getUsername(), dto.getPassword(),
			new Email(dto.getEmailAddress()),
			new Address(dto.getCity(), dto.getStreet(), dto.getZipcode()));

		final String token = JwtUtil.newApiToken(jwt, user);
		return new ApiResult<>(
			new RegisterResponseDto(token, user.getUsername(), user.getEmailAddress()));
	}
}
