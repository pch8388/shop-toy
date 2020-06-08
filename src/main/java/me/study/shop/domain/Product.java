package me.study.shop.domain;

import lombok.*;
import me.study.shop.exception.NotEnoughStockException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Product {

	@Id @GeneratedValue
	@Column(name = "product_id")
	private Long id;
	private String title;
	private int price;
	private int stockQuantity;

	public void removeStock(int quantity) {
		int restStock = stockQuantity - quantity;
		if (restStock < 0) {
			throw new NotEnoughStockException();
		}

		this.stockQuantity = restStock;
	}

	public void checkStock() {
		if (stockQuantity <= 0) {
			throw new NotEnoughStockException();
		}
	}
}


