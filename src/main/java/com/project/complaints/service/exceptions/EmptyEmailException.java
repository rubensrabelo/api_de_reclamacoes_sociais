package com.project.complaints.service.exceptions;

import java.io.Serial;

public class EmptyEmailException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyEmailException(String message) {
        super(message);
    }
}
