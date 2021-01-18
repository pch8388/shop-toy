package me.study.shop.authentication.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.study.shop.authentication.dto.AuthenticationRequestDto;
import me.study.shop.authentication.dto.AuthenticationResponseDto;
import me.study.shop.common.ApiResult;
import me.study.shop.security.AuthenticationResult;
import me.study.shop.security.JwtAuthenticationToken;

@RestController
@RequiredArgsConstructor
public class AuthenticationApi {

	private final AuthenticationManager authenticationManager;

	@PostMapping("/api/login")
	public ApiResult<AuthenticationResponseDto> authentication(@RequestBody AuthenticationRequestDto authRequest) {
		final JwtAuthenticationToken authenticationToken =
			new JwtAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredential());
		final Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		return new ApiResult<>(
			new AuthenticationResponseDto(
				(AuthenticationResult) authenticate.getDetails()));
	}
}
