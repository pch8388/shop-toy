package me.study.shop.dto;

import lombok.Data;
import me.study.shop.domain.Address;

@Data
public class MemberRequestDto {
	private String username;
	private Address address;
}
