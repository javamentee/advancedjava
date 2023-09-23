package com.user.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserDoesntFound extends RuntimeException {
	private static final long serialVersionUID = 9029834292157200901L;

	public UserDoesntFound(String msg) {
		super(msg);

	}
}