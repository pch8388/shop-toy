package me.study.shop.member.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@EqualsAndHashCode(of = {"city", "street", "zipcode"})
@ToString(of = {"city", "street", "zipcode"})
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Address {
	private String city;
	private String street;
	private String zipcode;
}
