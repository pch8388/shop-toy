package me.study.shop.product.dto;

import lombok.*;
import me.study.shop.product.domain.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class ProductRequestDto {
	@NotEmpty
	private String title;

	private int price;

	@Min(1)
	private int stockQuantity;

	public Product toEntity() {
		return Product.createProduct(title, price, stockQuantity);
	}
}
