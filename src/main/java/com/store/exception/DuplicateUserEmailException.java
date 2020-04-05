package com.store.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DuplicateUserEmailException extends Exception {

    public DuplicateUserEmailException() {
        super();
    }

    public DuplicateUserEmailException(String message) {
        super(message);
    }

    public DuplicateUserEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateUserEmailException(Throwable cause) {
        super(cause);
    }

    protected DuplicateUserEmailException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
