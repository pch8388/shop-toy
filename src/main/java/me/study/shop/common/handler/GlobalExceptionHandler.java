package me.study.shop.common.handler;

import me.study.shop.common.exception.BusinessException;
import me.study.shop.common.exception.ErrorCode;
import me.study.shop.common.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	/** 비지니스 예외 */
	@ExceptionHandler(BusinessException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
		final ErrorCode errorCode = e.getErrorCode();
		final ErrorResponse response = ErrorResponse.of(errorCode);

		return new ResponseEntity<>(response, errorCode.getStatus());
	}

	/** Validate 예외 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		final MethodArgumentNotValidException e) {

		final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
		final ErrorResponse response = ErrorResponse.of(errorCode, e.getBindingResult());

		return new ResponseEntity<>(response, errorCode.getStatus());
	}
}
