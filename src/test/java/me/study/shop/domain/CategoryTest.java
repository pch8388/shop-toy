package me.study.shop.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @Test
    @DisplayName("하위 카테고리 등록")
    public void category_addChild() {
        final Category parent = Category.of("Parent1");

        final Category child1 = Category.of("Child1");
        final Category child2 = Category.of("Child2");

        parent.addChildCategory(child1);
        parent.addChildCategory(child2);

        final Collection<Category> child = parent.getChild();
        assertThat(child).extracting("categoryName")
            .containsExactly("Child1", "Child2");
    }
}