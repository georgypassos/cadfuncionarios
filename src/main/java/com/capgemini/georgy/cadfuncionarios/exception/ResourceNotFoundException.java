package com.capgemini.georgy.cadfuncionarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 5094029005667638446L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
