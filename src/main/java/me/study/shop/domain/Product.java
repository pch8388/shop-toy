package me.study.shop.domain;

import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Builder
public class Product {

	@Id @GeneratedValue
	private Long id;
	private String name;
	private int price;

}
