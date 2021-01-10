package me.study.shop.member.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundMemberException extends BusinessException {

	public NotFoundMemberException() {
		super(ErrorCode.NOT_FOUND_MEMBER);
	}
}
