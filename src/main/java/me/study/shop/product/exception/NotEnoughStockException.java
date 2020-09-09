package me.study.shop.product.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotEnoughStockException extends BusinessException {

	public NotEnoughStockException() {
		super(ErrorCode.NOT_ENOUGH_STOCK);
	}

}
