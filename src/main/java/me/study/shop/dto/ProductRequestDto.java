package me.study.shop.dto;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
public class ProductRequestDto {
	@NotEmpty
	private String title;

	private int price;

	@Min(1)
	private int stockQuantity;
}
