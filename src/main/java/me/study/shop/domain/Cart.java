package me.study.shop.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
public class Cart {

	private static final int CART_COUNT = 1;

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
		product.removeStock(CART_COUNT);

		return new Cart(member, product);
	}
}
