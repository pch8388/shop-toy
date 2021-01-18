package me.study.shop.cart.service;

import me.study.shop.user.domain.Address;
import me.study.shop.cart.domain.Cart;
import me.study.shop.user.domain.Email;
import me.study.shop.user.domain.User;
import me.study.shop.product.domain.Product;
import me.study.shop.cart.repository.CartRepository;
import me.study.shop.user.repository.UserRepository;
import me.study.shop.product.repository.ProductRepository;
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
import static org.assertj.core.api.Assertions.filter;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith({MockitoExtension.class})
class CartServiceTest {

	@Mock
	private CartRepository cartRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private CartService cartService;

	@Test
	@DisplayName("장바구니에 상품을 담는다")
	public void save_cart() {
		final User user = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(user, product);

		given(productRepository.findById(1L)).willReturn(Optional.of(product));
		given(userRepository.findById(1L)).willReturn(Optional.of(user));
		given(cartRepository.save(any())).willReturn(cart);

		cartService.saveCart(1L, 1L);

		verify(cartRepository).save(any(Cart.class));
	}

	@Test
	@DisplayName("장바구니를 삭제한다")
	public void delete_cart() {
		final User user = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(user, product);

		given(cartRepository.findById(1L)).willReturn(Optional.of(cart));

		cartService.deleteCart(1L);

		verify(cartRepository).delete(any(Cart.class));
	}

	@Test
	@DisplayName("장바구니 목록 조회")
	public void list_cart() {
		final User user = User.createUser(
			"member1", "test", new Email("test@test.com"),
			new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(user, product);
		final List<Cart> carts = Collections.singletonList(cart) ;

		Page<Cart> mockPage = new PageImpl<>(carts);
		PageRequest pageRequest = PageRequest.of(0, 3);
		given(cartRepository.findAllByUser(user, pageRequest)).willReturn(mockPage);
		given(userRepository.findById(1L)).willReturn(Optional.of(user));

		final Page<Cart> pageCart = cartService.findAllByMemberId(1L, pageRequest);
		assertThat(pageCart.getSize()).isEqualTo(1);
		assertThat(pageCart.getContent()).contains(cart);

		verify(cartRepository).findAllByUser(user, pageRequest);
	}
}