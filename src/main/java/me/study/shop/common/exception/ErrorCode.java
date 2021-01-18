package me.study.shop.common.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

	/** common */
	INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

	/** product */
	NOT_FOUND_PRODUCT(HttpStatus.NOT_FOUND, "해당하는 상품이 없습니다."),
	NOT_ENOUGH_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족합니다."),
	QUANTITY_PARAMETER(HttpStatus.BAD_REQUEST, "0이하로 재고를 추가할 수 없습니다."),

	/** user */
	NOT_FOUND_USER(HttpStatus.BAD_REQUEST, "해당하는 회원이 없습니다."),
	NOT_FOUND_EMAIL(HttpStatus.BAD_REQUEST, "해당하는 이메일이 없습니다."),
	EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 등록된 이메일 입니다."),

	/** cart */
	NOT_FOUND_CART(HttpStatus.NOT_FOUND, "존재하지 않는 장바구니 입니다."),

	/** category */
	NOT_FOUND_CATEGORY(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리 입니다.")
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
