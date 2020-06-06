package me.study.shop.service;

import me.study.shop.domain.Product;
import me.study.shop.repository.ProductRepository;
import me.study.shop.exception.ErrorCode;
import me.study.shop.exception.NotFoundProductException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
			.title("test")
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
			.title("test")
			.price(10000)
			.build();

		List<Product> products = Collections.singletonList(product);

		Page<Product> mockPage = new PageImpl<>(products);
		PageRequest pageRequest = PageRequest.of(0, 3);
		when(productRepository.findAll(pageRequest)).thenReturn(mockPage);

		final Page<Product> findProducts = productService.findProducts(pageRequest);

		assertThat(findProducts.getSize()).isEqualTo(1);
		assertThat(findProducts.getContent()).contains(product);

		verify(productRepository).findAll(pageRequest);
	}

	@Test
	@DisplayName("상품 상세 조회")
	public void findProduct() {
		final long id = 1L;
		final Product product = Product.builder()
			.id(id)
			.title("test")
			.price(10000)
			.build();

		when(productRepository.findById(id)).thenReturn(Optional.of(product));

		final Product findProduct = productService.findProduct(id);

		assertThat(findProduct).isEqualTo(product);

		verify(productRepository).findById(id);
	}

	@Test
	@DisplayName("상품 상세 조회 - 조회할 상품이 존재하지 않으면 예외발생")
	public void findProduct_NotFoundProductException() {
		final Long id = 1L;

		when(productRepository.findById(id)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> productService.findProduct(id))
			.isInstanceOf(NotFoundProductException.class)
			.hasMessage(ErrorCode.NOT_FOUND_PRODUCT.getMessage());
	}
}