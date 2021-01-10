package me.study.shop.cart.service;

import me.study.shop.cart.service.CartService;
import me.study.shop.member.domain.Address;
import me.study.shop.cart.domain.Cart;
import me.study.shop.member.domain.Member;
import me.study.shop.product.domain.Product;
import me.study.shop.cart.repository.CartRepository;
import me.study.shop.member.repository.MemberRepository;
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
	private MemberRepository memberRepository;

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private CartService cartService;

	@Test
	@DisplayName("장바구니에 상품을 담는다")
	public void save_cart() {
		final Member member = Member.createMember(
			"member1", "test", new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(member, product);

		given(productRepository.findById(1L)).willReturn(Optional.of(product));
		given(memberRepository.findById(1L)).willReturn(Optional.of(member));
		given(cartRepository.save(any())).willReturn(cart);

		cartService.saveCart(1L, 1L);

		verify(cartRepository).save(any(Cart.class));
	}

	@Test
	@DisplayName("장바구니를 삭제한다")
	public void delete_cart() {
		final Member member = Member.createMember(
			"member1", "test", new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(member, product);

		given(cartRepository.findById(1L)).willReturn(Optional.of(cart));

		cartService.deleteCart(1L);

		verify(cartRepository).delete(any(Cart.class));
	}

	@Test
	@DisplayName("장바구니 목록 조회")
	public void list_cart() {
		final Member member = Member.createMember(
			"member1", "test", new Address("Seoul", "road", "12345"));

		final Product product = Product.createProduct(
			"test", 10000, 100);

		final Cart cart = Cart.addToCart(member, product);
		final List<Cart> carts = Collections.singletonList(cart) ;

		Page<Cart> mockPage = new PageImpl<>(carts);
		PageRequest pageRequest = PageRequest.of(0, 3);
		given(cartRepository.findAllByMember(member, pageRequest)).willReturn(mockPage);
		given(memberRepository.findById(1L)).willReturn(Optional.of(member));

		final Page<Cart> pageCart = cartService.findAllByMemberId(1L, pageRequest);
		assertThat(pageCart.getSize()).isEqualTo(1);
		assertThat(pageCart.getContent()).contains(cart);

		verify(cartRepository).findAllByMember(member, pageRequest);
	}
}