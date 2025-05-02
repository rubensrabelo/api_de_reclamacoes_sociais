package com.project.complaints.service.exceptions;

import java.io.Serial;

public class InvalidTitleSizeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidTitleSizeException(String message) {
        super(message);
    }
}
