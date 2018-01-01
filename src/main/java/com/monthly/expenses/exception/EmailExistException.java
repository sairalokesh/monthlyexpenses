package com.monthly.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SEE_OTHER)
public class EmailExistException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 5736162823060728732L;

    /**
     * Instantiates a new BadRequestException exception.
     *
     * @param objectGroupId
     *            the object group id
     */
    public EmailExistException() {
        super();
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param message
     *            the message
     */
    public EmailExistException(String message) {
        super(message);
    }


}
