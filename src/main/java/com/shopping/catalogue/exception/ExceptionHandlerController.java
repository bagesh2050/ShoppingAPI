package com.shopping.catalogue.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.shopping.catalogue.helper.ReturnCode;

@ControllerAdvice
public class ExceptionHandlerController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(HttpServletRequest req,
			MethodArgumentNotValidException ex) {
		final List<String> errors = new ArrayList<>();
		final List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		if (!fieldErrors.isEmpty()) {
			errors.add(fieldErrors.get(0).getField() + ": " + fieldErrors.get(0).getDefaultMessage());
		}
		if (errors.isEmpty()) {
			List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors();
			if (!objectErrors.isEmpty()) {
				errors.add(objectErrors.get(0).getDefaultMessage());
			}
		}
		String description = String.join(",", errors);
		return makeResponse(req, ex, ReturnCode.INTERNAL_ERROR_BAD_REQUEST, description);
	}

	@ExceptionHandler(GenericBusinessException.class)
	protected ResponseEntity<ApiErrorResponse> handleGenericNotFoundException(HttpServletRequest req,
			GenericBusinessException ex) {
		return makeResponse(req, ex, ex.getCode(), ex.getDescription());
	}

	private ResponseEntity<ApiErrorResponse> makeResponse(HttpServletRequest req, Exception ex, ReturnCode returnCode,
			String description) {
		ResponseEntity<ApiErrorResponse> responseBody = getErrorResponse(returnCode, description);
		req.setAttribute("isExceptionHandled", true);
		req.setAttribute("exceptionHandled", ex);
		req.setAttribute("errorResponseBody", responseBody.toString());
		return responseBody;
	}

	private ResponseEntity<ApiErrorResponse> getErrorResponse(ReturnCode returnCode, String description) {
		final ApiErrorResponse apiErrorResponse = new ApiErrorResponse(returnCode, description);
		apiErrorResponse.setMessage("Exception Occured");
		return new ResponseEntity<>(apiErrorResponse, returnCode.getHttpStatus());
	}
}