package me.study.shop.service;

import me.study.shop.domain.Product;
import me.study.shop.domain.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService productService;

	@Test
	@DisplayName("상품을 등록한다")
	public void save() {
		Product product = Product.builder()
			.id(1L)
			.name("test")
			.price(10000)
			.build();

		given(productRepository.save(any())).willReturn(product);

		productService.save(product);

		verify(productRepository).save(any(Product.class));
	}

	@Test
	@DisplayName("상품 목록 조회")
	public void findProducts() {
		final Product product = Product.builder()
			.id(1L)
			.name("test")
			.price(10000)
			.build();

		List<Product> products = Collections.singletonList(product);

		Page<Product> mockPage = new PageImpl<>(products);
		PageRequest pageRequest = PageRequest.of(0, 3);
		when(productRepository.findAll(pageRequest)).thenReturn(mockPage);

		final Page<Product> findProducts = productRepository.findAll(pageRequest);

		assertThat(findProducts.getSize()).isEqualTo(1);
		assertThat(findProducts.getContent()).contains(product);

		verify(productRepository).findAll(pageRequest);
	}
}