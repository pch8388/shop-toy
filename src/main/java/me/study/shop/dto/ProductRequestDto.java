package me.study.shop.dto;

import lombok.*;
import me.study.shop.domain.Product;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductRequestDto {
	private String name;
	private int price;

	public Product toEntity() {
		return Product.builder()
			.name(name)
			.price(price)
			.build();
	}
}
