package me.study.shop.product.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class StockQuantityParameterException extends BusinessException {

    public StockQuantityParameterException() {
        super(ErrorCode.QUANTITY_PARAMETER);
    }

}
