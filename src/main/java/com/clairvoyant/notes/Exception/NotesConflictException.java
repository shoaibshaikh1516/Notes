package com.clairvoyant.notes.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class NotesConflictException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NotesConflictException(String exception) {
        super(exception);
    }

}
