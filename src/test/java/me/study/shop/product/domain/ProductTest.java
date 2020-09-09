package me.study.shop.product.domain;

import me.study.shop.common.exception.ErrorCode;
import me.study.shop.product.exception.NotEnoughStockException;
import me.study.shop.product.exception.StockQuantityParameterException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

	@ParameterizedTest
	@DisplayName("재고 수량을 증가 시킴")
	@ValueSource(ints = {5, 10, 20, 100})
	public void addStock(int quantity) {
		int stockQuantity = 10;
		Product product = Product.createProduct("TEST1", 10000, stockQuantity);

		product.addStock(quantity);
		assertThat(product.getStockQuantity()).isEqualTo(stockQuantity + quantity);
	}

	@ParameterizedTest
	@DisplayName("재고 수량을 증가 시킴 - 0 이하의 값이 들어오면 예외처리")
	@ValueSource(ints = {0, -10, -100})
	public void addStock_negative_exception(int quantity) {
		int stockQuantity = 10;
		Product product = Product.createProduct("TEST1", 10000, stockQuantity);

		assertThatThrownBy(() -> product.addStock(quantity))
			.isInstanceOf(StockQuantityParameterException.class)
			.hasMessage("0이하로 재고를 추가할 수 없습니다.");
	}
}