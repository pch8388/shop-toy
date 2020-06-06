package me.study.shop.exception;

public class NotFoundMemberException extends BusinessException {

	public NotFoundMemberException() {
		super(ErrorCode.NOT_FOUND_MEMBER);
	}
}
