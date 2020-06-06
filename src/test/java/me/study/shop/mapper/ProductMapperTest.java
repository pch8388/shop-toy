package me.study.shop.mapper;

import me.study.shop.domain.Product;
import me.study.shop.dto.ProductRequestDto;
import me.study.shop.dto.ProductResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class ProductMapperTest {

	@Test
	@DisplayName("dto -> entity 변환")
	public void toProductEntity() {
		final ProductRequestDto dto = new ProductRequestDto();
		dto.setName("test");
		dto.setPrice(10000);

		final Product product = ProductMapper.INSTANCE.toProductEntity(dto);
		assertThat(product.getName()).isEqualTo(dto.getName());
		assertThat(product.getPrice()).isEqualTo(dto.getPrice());
	}

	@Test
	@DisplayName("entity -> dto 변환")
	public void toProductResponseDto() {
		final Product product = Product.builder()
			.id(1L)
			.price(1000)
			.name("test")
			.build();

		final ProductResponseDto dto =
			ProductMapper.INSTANCE.toProductResponseDto(product);
		assertThat(dto.getId()).isEqualTo(product.getId());
		assertThat(dto.getName()).isEqualTo(product.getName());
		assertThat(dto.getPrice()).isEqualTo(product.getPrice());
	}
}