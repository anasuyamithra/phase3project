package com.fr.p3p.utils;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.servlet.http.HttpServletResponse;

@ControllerAdvice
@RestController
public class MSExceptionHandler {

	/**
	 * Returns custom error response on occurrence of custom exception.
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MSException.class)
	public MSException handleContentNotFoundException(MSException e, HttpServletResponse response) {
		response.setStatus(e.getErrorCode());
		MSException error = new MSException(e.getErrorCode(), e.getMessage());
		return error;
	}

	/**
	 * Returns custom error response on occurrence of exception.
	 * 
	 * @param e
	 * @return
	 */
//	@ExceptionHandler(value = Exception.class)
//	public ErrorResponse handleException(Exception e, HttpServletResponse response) {
//		int errorCode = ErrorCode.INTERNAL_SERVER_ERROR.getCode();
//		  if (e instanceof HttpRequestMethodNotSupportedException) {
//			  errorCode = ErrorCode.METHOD_NOT_SUPPORTED.getCode();
//		  }
//		response.setStatus(errorCode);
//		ErrorResponse error = new ErrorResponse();
//		error.setMessage(e.getMessage());
//		error.setErrorCode(errorCode);
//		return error;
//	}

	/**
	 * Returns custom error response on occurrence of data integrity violation
	 * exception.
	 * 
	 * @param e
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public MSException handleDataIntegrityException(ConstraintViolationException e) {
		String message = e.getCause().getMessage();
		if (message != null && message.contains("=")) {
			String[] messages = message.split("=");
			if (messages.length == 2) {
				String value = messages[1];
				int startIndex = value.indexOf('(') + 1;
				int endIndex = value.lastIndexOf(')');
				value = value.substring(startIndex, endIndex);
				if (value.contains(",")) {
					String[] values = value.split(",");
					message = values[0] + " already exists";
				} else {
					message = value + " already exists";
				}

			}
		}
		MSException error = new MSException(ErrorCode.CONSTRAINT_VIOLATION.getCode(), message == null ? "Constraint violation" : message);
		return error;
	}

	/**
	 * Returns custom error response on occurrence spring validation exception.
	 * 
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MSException handleValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();

		StringBuilder builder = new StringBuilder();
		for (FieldError fError : fieldErrors) {
			if (!builder.toString().isEmpty()) {
				builder.append(", ");
			}
			builder.append(fError.getField());
			builder.append(" ");
			builder.append(fError.getDefaultMessage());
		}
		MSException error = new MSException(ErrorCode.BAD_REQUEST.getCode(), builder.toString());
		return error;
	}

}
