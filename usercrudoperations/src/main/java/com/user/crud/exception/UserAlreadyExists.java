package com.user.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class UserAlreadyExists extends RuntimeException {

	private static final long serialVersionUID = 8114578708529654099L;

	public UserAlreadyExists(String msg) {
		super(msg);
	}
}
