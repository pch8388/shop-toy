package me.study.shop.exception;

public class NotEnoughStockException extends BusinessException {

	public NotEnoughStockException() {
		super(ErrorCode.NOT_ENOUGH_STOCK);
	}

}
