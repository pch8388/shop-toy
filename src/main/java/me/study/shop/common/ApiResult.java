package me.study.shop.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class ApiResult<T> {

	@ApiModelProperty(value = "반환할 데이터", required = true)
	private final T data;

	public ApiResult(T data) {
		this.data = data;
	}
}
