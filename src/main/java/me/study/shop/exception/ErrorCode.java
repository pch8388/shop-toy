package me.study.shop.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	/** common */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 요청입니다"),

	/** product */
	NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "해당하는 상품이 없습니다"),
	NOT_ENOUGH_STOCK(HttpStatus.NOT_FOUND, "재고가 부족합니다"),

	/** member */
	NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "해당하는 회원이 없습니다"),

	/** cart */
	NOT_FOUND_CART(HttpStatus.NOT_FOUND, "존재하지 않는 장바구니 입니다")
	;

	private final HttpStatus status;
	private final String message;

	ErrorCode(final HttpStatus status, final String message) {
		this.status = status;
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}
}
