package me.study.shop.product.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import me.study.shop.product.domain.Product;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
public class ProductRequestDto {
	@NotEmpty
	@ApiModelProperty(value = "상품명")
	private String title;

	@ApiModelProperty(value = "상품가격")
	private int price;

	@Min(1)
	@ApiModelProperty(value = "재고수량")
	private int stockQuantity;

	public Product toEntity() {
		return Product.createProduct(title, price, stockQuantity);
	}
}
