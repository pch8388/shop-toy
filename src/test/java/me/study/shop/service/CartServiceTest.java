package me.study.shop.service;

import me.study.shop.domain.Address;
import me.study.shop.domain.Cart;
import me.study.shop.domain.Member;
import me.study.shop.domain.Product;
import me.study.shop.repository.CartRepository;
import me.study.shop.repository.MemberRepository;
import me.study.shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

	@BeforeEach
	public void setUp() {
		mockMember();
		mockProduct();
	}

	private void mockProduct() {
		final Product product = Product.builder()
			.id(1L)
			.title("test")
			.price(10000)
			.build();

		given(productRepository.findById(1L)).willReturn(Optional.of(product));
	}

	private void mockMember() {
		final Member member = Member.builder()
			.id(1L)
			.username("member1")
			.address(new Address("Seoul", "road", "12345"))
			.build();

		given(memberRepository.findById(1L)).willReturn(Optional.of(member));
	}

	@Test
	@DisplayName("장바구니에 상품을 담는다")
	public void save_cart() {
		final Member member = Member.builder()
			.id(1L)
			.username("member1")
			.address(new Address("Seoul", "road", "12345"))
			.build();

		final Product product = Product.builder()
			.id(1L)
			.title("test")
			.price(10000)
			.build();

		final Cart cart = Cart.builder()
			.id(1L)
			.member(member)
			.product(product)
			.build();

		given(cartRepository.save(any())).willReturn(cart);

		cartService.saveCart(member.getId(), product.getId());

		verify(cartRepository).save(any(Cart.class));
	}
}