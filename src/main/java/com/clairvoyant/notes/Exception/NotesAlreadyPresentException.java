package com.clairvoyant.notes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class NotesAlreadyPresentException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotesAlreadyPresentException(String exception) {
        super(exception);
    }

}
