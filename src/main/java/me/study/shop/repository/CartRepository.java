package me.study.shop.repository;

import me.study.shop.domain.Cart;
import me.study.shop.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
	Page<Cart> findAllByMember(Member member, Pageable pageable);
}
