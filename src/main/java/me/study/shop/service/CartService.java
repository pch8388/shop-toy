package me.study.shop.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.domain.Cart;
import me.study.shop.domain.Member;
import me.study.shop.domain.Product;
import me.study.shop.exception.NotFoundCartException;
import me.study.shop.exception.NotFoundMemberException;
import me.study.shop.exception.NotFoundProductException;
import me.study.shop.repository.CartRepository;
import me.study.shop.repository.MemberRepository;
import me.study.shop.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartService {

	private final CartRepository cartRepository;
	private final MemberRepository memberRepository;
	private final ProductRepository productRepository;

	@Transactional
	public Cart saveCart(Long memberId, Long productId) {
		final Member member = memberRepository.findById(memberId)
			.orElseThrow(NotFoundMemberException::new);

		final Product product = productRepository.findById(productId)
				.orElseThrow(NotFoundProductException::new);

		return cartRepository.save(
			Cart.builder()
				.member(member)
				.product(product)
				.build());
	}

	@Transactional
	public void deleteCart(Long cartId) {
		final Cart cart = cartRepository.findById(cartId)
			.orElseThrow(NotFoundCartException::new);

		cartRepository.delete(cart);
	}
}
