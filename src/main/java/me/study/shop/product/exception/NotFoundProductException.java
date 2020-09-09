package me.study.shop.product.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundProductException extends BusinessException {

	public NotFoundProductException() {
		super(ErrorCode.NOT_FOUND_PRODUCT);
	}
}
