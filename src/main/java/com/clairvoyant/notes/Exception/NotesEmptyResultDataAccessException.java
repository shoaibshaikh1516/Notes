package com.clairvoyant.notes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotesEmptyResultDataAccessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotesEmptyResultDataAccessException(String exception) {
        super(exception);
    }

}
