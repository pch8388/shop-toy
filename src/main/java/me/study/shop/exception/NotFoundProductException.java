package me.study.shop.exception;

public class NotFoundProductException extends BusinessException {

	public NotFoundProductException() {
		super(ErrorCode.NOT_FOUND_PRODUCT);
	}
}
