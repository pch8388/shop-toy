package me.study.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor
public class Address {
	private String city;
	private String street;
	private String zipcode;
}
