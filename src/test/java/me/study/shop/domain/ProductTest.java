package me.study.shop.domain;

import me.study.shop.exception.ErrorCode;
import me.study.shop.exception.NotEnoughStockException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProductTest {

	@Test
	@DisplayName("재고 수량보다 작거나 같은 상품을 요청하면 상품 수량이 줄어듬")
	public void removeStock() {
		Product product = Product.createProduct("test1", 1000, 5);

		product.removeStock(3);
		assertThat(product.getStockQuantity()).isEqualTo(2);

		product.removeStock(2);
		assertThat(product.getStockQuantity()).isEqualTo(0);

	}

	@Test
	@DisplayName("재고 수량보다 많은 상품을 요청하면 예외를 발생")
	public void removeStock_NotEnoughException() {
		Product product = Product.createProduct("test1", 1000, 5);

		assertThatThrownBy(() -> product.removeStock(10))
			.isInstanceOf(NotEnoughStockException.class)
			.hasMessage(ErrorCode.NOT_ENOUGH_STOCK.getMessage());
	}

	@Test
	@DisplayName("현재 재고 수량이 있는지 확인 - 없으면 예외 발생")
	public void checkStock_NotEnoughException() {
		Product product = Product.createProduct("test1", 1000, 0);

		assertThatThrownBy(product::checkStock)
			.isInstanceOf(NotEnoughStockException.class)
			.hasMessage(ErrorCode.NOT_ENOUGH_STOCK.getMessage());
	}
}