package me.study.shop.user.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundEmailException extends BusinessException {

	public NotFoundEmailException() {
		super(ErrorCode.NOT_FOUND_EMAIL);
	}
}
