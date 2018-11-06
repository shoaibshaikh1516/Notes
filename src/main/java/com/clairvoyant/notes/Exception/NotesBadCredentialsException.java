package com.clairvoyant.notes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotesBadCredentialsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotesBadCredentialsException(String exception) {
		super(exception);
	}

}
