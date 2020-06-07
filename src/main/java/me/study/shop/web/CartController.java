package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.CartRequestDto;
import me.study.shop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CartController {

	private final CartService cartService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/cart")
	public Long add(@RequestBody @Valid CartRequestDto dto) {
		return cartService.saveCart(
			dto.getMemberId(), dto.getProductId()).getId();
	}
}
