package me.study.shop.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.study.shop.dto.CartRequestDto;
import me.study.shop.dto.CartResponseDto;
import me.study.shop.service.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"1. Cart"})
@RestController
@RequiredArgsConstructor
public class CartApi {

	private final CartService cartService;

	@ApiOperation(value = "장바구니 등록", notes = "장바구니에 상품을 담는다")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/api/v1/carts")
	public Long add(@RequestBody @Valid CartRequestDto dto) {
		return cartService.saveCart(
			dto.getMemberId(), dto.getProductId()).getId();
	}

	@ApiOperation(value = "장바구니 삭제", notes = "장바구니 상품을 삭제한다")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/api/v1/carts/{cartId}")
	public void delete(@PathVariable Long cartId) {
		cartService.deleteCart(cartId);
	}

	@ApiOperation(value = "장바구니 조회", notes = "장바구니 상품을 조회한다")
	@GetMapping("/api/v1/carts/{memberId}")
	public Page<CartResponseDto> list(
		@PathVariable Long memberId, Pageable pageable) {

		return cartService.findAllByMemberId(memberId, pageable)
			.map(CartResponseDto::of);
	}
}
