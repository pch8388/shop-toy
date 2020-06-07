package me.study.shop.web;

import lombok.RequiredArgsConstructor;
import me.study.shop.dto.CartRequestDto;
import me.study.shop.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/api/v1/cart/{cartId}")
	public void delete(@PathVariable Long cartId) {
		cartService.deleteCart(cartId);
	}
}
