package me.study.shop.cart.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CartRequestDto {
	@NotNull
	@ApiModelProperty(value = "장바구니에 상품을 담을 유저 id", required = true)
	private Long userId;
	@NotNull
	@ApiModelProperty(value = "장바구니에 담을 상품 id", required = true)
	private Long productId;
}
