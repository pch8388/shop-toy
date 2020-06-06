package me.study.shop.dto;

import lombok.*;

@Data
public class ProductResponseDto {
	private Long id;
	private String title;
	private int price;
	private int stockQuantity;
}
