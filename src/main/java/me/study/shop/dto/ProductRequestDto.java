package me.study.shop.dto;

import lombok.*;

@Data
public class ProductRequestDto {
	private String title;
	private int price;
	private int stockQuantity;
}
