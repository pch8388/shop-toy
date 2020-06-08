package me.study.shop.dto;

import lombok.Data;
import me.study.shop.domain.Address;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberRequestDto {
	@NotEmpty
	private String username;
	private Address address;
}
