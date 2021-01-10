package me.study.shop.cart.repository;

import me.study.shop.cart.domain.Cart;
import me.study.shop.member.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Page<Cart> findAllByMember(Member member, Pageable pageable);
}
