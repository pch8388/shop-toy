package me.study.shop.cart.service;

import lombok.RequiredArgsConstructor;
import me.study.shop.cart.domain.Cart;
import me.study.shop.user.domain.User;
import me.study.shop.product.domain.Product;
import me.study.shop.cart.exception.NotFoundCartException;
import me.study.shop.user.exception.NotFoundUserException;
import me.study.shop.product.exception.NotFoundProductException;
import me.study.shop.cart.repository.CartRepository;
import me.study.shop.user.repository.UserRepository;
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
	private final UserRepository userRepository;
	private final ProductRepository productRepository;

	@Transactional
	public Cart saveCart(Long userId, Long productId) {
		final User user = userRepository.findById(userId)
			.orElseThrow(NotFoundUserException::new);

		final Product product = productRepository.findById(productId)
				.orElseThrow(NotFoundProductException::new);

		return cartRepository.save(Cart.addToCart(user, product));
	}

	@Transactional
	public void deleteCart(Long cartId) {
		final Cart cart = cartRepository.findById(cartId)
			.orElseThrow(NotFoundCartException::new);

		cartRepository.delete(cart);
	}

	public Page<Cart> findAllByUserId(Long userId, Pageable pageable) {
		return cartRepository.findAllByUser(
			userRepository.findById(userId)
				.orElseThrow(NotFoundUserException::new), pageable);
	}
}
