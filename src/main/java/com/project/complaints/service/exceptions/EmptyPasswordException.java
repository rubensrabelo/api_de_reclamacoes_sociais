package com.project.complaints.service.exceptions;

import java.io.Serial;

public class EmptyPasswordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyPasswordException(String message) {
        super(message);
    }
}
