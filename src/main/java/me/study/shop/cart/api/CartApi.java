package me.study.shop.cart.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import me.study.shop.cart.dto.CartRequestDto;
import me.study.shop.cart.dto.CartResponseDto;
import me.study.shop.cart.service.CartService;
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

	@PostMapping("/api/v1/carts")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "장바구니 등록", notes = "장바구니에 상품을 담는다")
	public Long add(@RequestBody @Valid CartRequestDto dto) {
		return cartService.saveCart(
			dto.getUserId(), dto.getProductId()).getId();
	}

	@DeleteMapping("/api/v1/carts/{cartId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "장바구니 삭제", notes = "장바구니 상품을 삭제한다")
	public void delete(@PathVariable @ApiParam(value = "삭제할 장바구니 id") Long cartId) {
		cartService.deleteCart(cartId);
	}

	@GetMapping("/api/v1/carts/{userId}")
	@ApiOperation(value = "장바구니 조회", notes = "장바구니 상품을 조회한다")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "userId", dataType = "long", value ="장바구니를 조회할 유저 id")
	})
	public Page<CartResponseDto> list(
		@PathVariable Long userId, Pageable pageable) {

		return cartService.findAllByUserId(userId, pageable)
			.map(CartResponseDto::of);
	}
}
