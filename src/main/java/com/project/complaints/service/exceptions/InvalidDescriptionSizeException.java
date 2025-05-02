package com.project.complaints.service.exceptions;

import java.io.Serial;

public class InvalidDescriptionSizeException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDescriptionSizeException(String message) {
        super(message);
    }
}
