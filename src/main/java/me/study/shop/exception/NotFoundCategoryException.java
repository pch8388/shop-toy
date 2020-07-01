package me.study.shop.exception;

public class NotFoundCategoryException extends BusinessException {

    public NotFoundCategoryException() {
        super(ErrorCode.NOUT_FOUND_CATEGORY);
    }

}
