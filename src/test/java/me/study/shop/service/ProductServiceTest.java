package me.study.shop.service;

import me.study.shop.domain.Product;
import me.study.shop.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	@DisplayName("상품을 등록한다")
	public void register() {
		Product product = Product.builder()
			.id(1L)
			.name("test")
			.price(10000)
			.build();

		given(productRepository.save(any())).willReturn(product);

		productService.register(product);

		verify(productRepository).save(any(Product.class));
	}
}