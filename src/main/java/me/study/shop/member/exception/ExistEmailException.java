package me.study.shop.member.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class ExistEmailException extends BusinessException {
	public ExistEmailException() {
		super(ErrorCode.EXIST_EMAIL);
	}
}
