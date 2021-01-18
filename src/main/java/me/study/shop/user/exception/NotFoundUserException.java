package me.study.shop.user.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundUserException extends BusinessException {

	public NotFoundUserException() {
		super(ErrorCode.NOT_FOUND_USER);
	}
}
