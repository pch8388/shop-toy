package me.study.shop.dto;

import lombok.Data;

@Data
public class CartRequestDto {
	private Long memberId;
	private Long productId;
}
