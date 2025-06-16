package com.transferjacaAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ElementNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public ElementNotFound(String message) {
		super(message);
	}

}
