package com.project.complaints.service.exceptions;

import java.io.Serial;

public class EmptyTitleException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmptyTitleException(String message) {
        super(message);
    }
}
