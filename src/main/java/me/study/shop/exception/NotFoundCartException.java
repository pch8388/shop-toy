package me.study.shop.exception;

public class NotFoundCartException extends BusinessException {

	public NotFoundCartException() {
		super(ErrorCode.NOT_FOUND_CART);
	}

}
