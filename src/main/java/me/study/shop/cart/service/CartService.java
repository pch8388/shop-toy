package me.study.shop.cart.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.cart.domain.Cart;
import me.study.shop.member.domain.Member;
import me.study.shop.product.domain.Product;
import me.study.shop.cart.exception.NotFoundCartException;
import me.study.shop.member.exception.NotFoundMemberException;
import me.study.shop.product.exception.NotFoundProductException;
import me.study.shop.cart.repository.CartRepository;
import me.study.shop.member.repository.MemberRepository;
import me.study.shop.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

		return cartRepository.save(Cart.addToCart(member, product));
	}

	@Transactional
	public void deleteCart(Long cartId) {
		final Cart cart = cartRepository.findById(cartId)
			.orElseThrow(NotFoundCartException::new);

		cartRepository.delete(cart);
	}

	public Page<Cart> findAllByMemberId(Long memberId, Pageable pageable) {
		return cartRepository.findAllByMember(
			memberRepository.findById(memberId)
				.orElseThrow(NotFoundMemberException::new), pageable);
	}
}
