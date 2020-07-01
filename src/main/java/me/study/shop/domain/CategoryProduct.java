package me.study.shop.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@Entity
public class CategoryProduct {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private CategoryProduct(Category category, Product product) {
        this.category = category;
        this.product = product;
    }

    public static CategoryProduct of(Category category, Product product) {
        return new CategoryProduct(category, product);
    }
}
