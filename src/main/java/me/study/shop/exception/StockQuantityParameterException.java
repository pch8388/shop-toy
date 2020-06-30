package me.study.shop.exception;

public class StockQuantityParameterException extends BusinessException {

    public StockQuantityParameterException() {
        super(ErrorCode.QUANTITY_PARAMETER);
    }

}
