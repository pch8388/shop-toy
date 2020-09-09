package me.study.shop.product.dto;

import lombok.*;
import me.study.shop.product.domain.Product;

@Getter
public class ProductResponseDto {
	private Long id;
	private String title;
	private int price;
	private int stockQuantity;

	private ProductResponseDto(Long id, String title, int price, int stockQuantity) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public static ProductResponseDto of(Product product) {
		return new ProductResponseDto(product.getId(),
			product.getTitle(), product.getPrice(),
			product.getStockQuantity());
	}
}
