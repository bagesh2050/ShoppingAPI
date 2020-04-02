package com.shopping.catalogue.helper;

import org.springframework.http.HttpStatus;

/*
 * Enumeration of response error codes
 * 
 * */
public enum ReturnCode {
	INTERNAL_ERROR(1, HttpStatus.INTERNAL_SERVER_ERROR),

	/**
	 * Generic messages
	 */

	SUCCESS_MESSAGE(200, HttpStatus.OK),

	/**
	 * generic technical errors
	 */
	INTERNAL_ERROR_BAD_REQUEST(3001, HttpStatus.BAD_REQUEST),
	INTERNAL_ERROR_DB(3002, HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final HttpStatus httpStatus;

	ReturnCode(int code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

	public static ReturnCode findByCode(int code) {
		for (ReturnCode returnCode : values()) {
			if (returnCode.getCode() == code) {
				return returnCode;
			}
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
