package com.clairvoyant.notes.Exception;

import com.clairvoyant.notes.Exception.Model.Message;
import com.clairvoyant.notes.Exception.Model.NotesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@ControllerAdvice
public class NotesCustomExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(NotesCustomExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(BadCredentialsException.class)
    @ResponseBody
    public Message handleCredentialsException(HttpServletRequest request, Exception ex) {
        logger.error(ex.getMessage(), ex);

        Message message = new Message(ex.getMessage(), new Date(), "Invalid Username/Password");

        return message;
    }

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Email ID Already Exists")
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public Object handleEmailIdAlreadyExistsException(HttpServletRequest request, Exception ex) {
        logger.error(ex.getMessage(), ex);
        NotesResponse exceptionResponse = new NotesResponse(ex.getMessage());
        return exceptionResponse;
    }

    @ExceptionHandler({NotesNotFoundException.class, NotesConflictException.class})
    @ResponseBody
    public Object handleNotFound(HttpServletRequest request, Exception ex) throws Exception {
        throw ex;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown Error Occurred. Please try again later!")
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleGlobalException(HttpServletRequest request, Exception ex) {
        logger.error(ex.getMessage(), ex);
        NotesResponse exceptionResponse = new NotesResponse(ex.getMessage());
        return exceptionResponse;
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public Object handleValidationError(HttpServletRequest request, Exception ex, Error error) throws Exception {
        logger.error(ex.getMessage(), ex);
        NotesResponse exceptionResponse = new NotesResponse(ex.getMessage());

        return exceptionResponse;
    }

}
