package me.study.shop.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
public class Product {

	@Getter
	@Id @GeneratedValue
	private Long id;
	private String name;
	private int price;

}
