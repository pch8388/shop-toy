package me.study.shop.product.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.shop.common.domain.BaseEntity;
import me.study.shop.product.exception.StockQuantityParameterException;
import me.study.shop.product.exception.NotEnoughStockException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Product extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "product_id")
	private Long id;

	@OneToMany(mappedBy = "product")
	private List<CategoryProduct> categoryProducts = new ArrayList<>();

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

	public void addStock(int quantity) {
		if (quantity <= 0) {
			throw new StockQuantityParameterException();
		}
		this.stockQuantity += quantity;
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


