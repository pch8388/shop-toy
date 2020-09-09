package me.study.shop.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.shop.member.domain.Member;
import me.study.shop.product.domain.Product;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart {

	@Id @GeneratedValue
	@Column(name = "cart_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="product_id")
	private Product product;

	private Cart(Member member, Product product) {
		this.member = member;
		this.product = product;
	}

	public static Cart addToCart(Member member, Product product) {
		product.checkStock();

		return new Cart(member, product);
	}
}
