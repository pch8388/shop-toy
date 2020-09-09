package me.study.shop.product.exception;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;

public class NotFoundCategoryException extends BusinessException {

    public NotFoundCategoryException() {
        super(ErrorCode.NOUT_FOUND_CATEGORY);
    }

}
