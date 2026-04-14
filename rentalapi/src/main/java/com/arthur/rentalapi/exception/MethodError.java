package com.arthur.rentalapi.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;


public class MethodError extends MethodArgumentNotValidException {
	public MethodError(MethodArgumentNotValidException ex) {
		super(ex.getParameter(), ex.getBindingResult());
	}
}
