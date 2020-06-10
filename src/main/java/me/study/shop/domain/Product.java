package me.study.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.shop.exception.NotEnoughStockException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product {

	@Id @GeneratedValue
	@Column(name = "product_id")
	private Long id;

	private String title;
	private int price;
	private int stockQuantity;

	private Product(String title, int price, int stockQuantity) {
		this.title = title;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}

	public static Product createProduct(String title, int price, int stockQuantity) {
		return new Product(title, price, stockQuantity);
	}

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


