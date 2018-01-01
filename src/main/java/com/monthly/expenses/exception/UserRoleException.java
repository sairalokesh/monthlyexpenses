package com.monthly.expenses.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UserRoleException extends RuntimeException {
    private static final long serialVersionUID = 7363042762491142285L;

    /**
     * Instantiates a new BadRequestException exception.
     *
     * @param objectGroupId
     *            the object group id
     */
    public UserRoleException() {
        super();
    }

    /**
     * Instantiates a new resource not found exception.
     *
     * @param message
     *            the message
     */
    public UserRoleException(String message) {
        super(message);
    }

}
