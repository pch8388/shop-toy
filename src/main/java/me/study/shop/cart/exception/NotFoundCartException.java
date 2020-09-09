package me.study.shop.cart.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundCartException extends BusinessException {

	public NotFoundCartException() {
		super(ErrorCode.NOT_FOUND_CART);
	}

}
