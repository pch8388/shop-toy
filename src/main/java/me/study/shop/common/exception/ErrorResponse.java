package me.study.shop.common.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

	private String message;
	private int status;
	private List<FieldError> errors;

	private ErrorResponse(final ErrorCode code, final List<FieldError> errors) {
		this.message = code.getMessage();
		this.status = code.getStatus().value();
		this.errors = errors;
	}

	private ErrorResponse(final ErrorCode code) {
		this.message = code.getMessage();
		this.status = code.getStatus().value();
		this.errors = new ArrayList<>();
	}

	public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
		return new ErrorResponse(code, FieldError.of(bindingResult));
	}

	public static ErrorResponse of(final ErrorCode code) {
		return new ErrorResponse(code);
	}

	public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
		final String value = Optional.ofNullable(e.getValue())
								.orElse("").toString();
		final List<ErrorResponse.FieldError> errors =
			ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());

		return new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE, errors);
	}

	@Getter
	@NoArgsConstructor(access = AccessLevel.PROTECTED)
	public static class FieldError {

		private String field;
		private String value;
		private String reason;

		private FieldError(final String field, final String value, final String reason) {
			this.field = field;
			this.value = value;
			this.reason = reason;
		}

		public static List<FieldError> of(final String field, final String value, final String reason) {
			return Collections.singletonList(new FieldError(field, value, reason));
		}

		private static List<FieldError> of(final BindingResult bindingResult) {
			return bindingResult.getFieldErrors()
					.stream()
					.map(error -> new FieldError(
						error.getField(),
						Optional.ofNullable(error.getRejectedValue()).orElse("").toString(),
						error.getDefaultMessage()))
					.collect(toList());
		}
	}
}
