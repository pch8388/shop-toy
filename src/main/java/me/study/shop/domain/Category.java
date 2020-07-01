package me.study.shop.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Category {

	@Id @GeneratedValue
	@Column(name = "category_id")
	private Long id;

	private String categoryName;

	@OneToMany(mappedBy = "category")
	private List<CategoryProduct> categoryProducts = new ArrayList<>();

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "parent_id")
	private Category parent;

	@OneToMany(mappedBy = "parent")
	private List<Category> child = new ArrayList<>();

	private Category(String categoryName) {
		this.categoryName = categoryName;
	}

	public static Category of(String categoryName) {
		return new Category(categoryName);
	}

	public void addChildCategory(Category child) {
		this.child.add(child);
		child.addParent(this);
	}

	private void addParent(Category parent) {
		this.parent = parent;
	}

	public Collection<Category> getChild() {
		return Collections.unmodifiableCollection(child);
	}
}
