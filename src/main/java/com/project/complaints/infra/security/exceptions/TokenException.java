package com.project.complaints.infra.security.exceptions;

public class TokenException extends RuntimeException {

    public TokenException(String message) {
        super(message);
    }
}
