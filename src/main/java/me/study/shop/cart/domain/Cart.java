package me.study.shop.cart.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.study.shop.common.domain.BaseEntity;
import me.study.shop.user.domain.User;
import me.study.shop.product.domain.Product;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Cart extends BaseEntity {

	@Id @GeneratedValue
	@Column(name = "cart_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="product_id")
	private Product product;

	private Cart(User user, Product product) {
		this.user = user;
		this.product = product;
	}

	public static Cart addToCart(User user, Product product) {
		product.checkStock();

		return new Cart(user, product);
	}
}
