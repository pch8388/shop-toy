package me.study.shop.cart.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartRequestDto {
	@NotNull
	private Long memberId;
	@NotNull
	private Long productId;
}
