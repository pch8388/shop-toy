package me.study.shop.cart.repository;

import me.study.shop.cart.domain.Cart;
import me.study.shop.user.domain.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Page<Cart> findAllByUser(User user, Pageable pageable);
}
