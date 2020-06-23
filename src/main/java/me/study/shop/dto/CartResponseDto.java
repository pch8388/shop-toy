package me.study.shop.dto;

import lombok.Getter;
import me.study.shop.domain.Cart;
import me.study.shop.domain.Product;

@Getter
public class CartResponseDto {
	private Long cartId;
	private Product product;

	private CartResponseDto(Long cartId, Product product) {
		this.cartId = cartId;
		this.product = product;
	}

	public static CartResponseDto of(Cart cart) {
		return new CartResponseDto(cart.getId(), cart.getProduct());
	}
}
