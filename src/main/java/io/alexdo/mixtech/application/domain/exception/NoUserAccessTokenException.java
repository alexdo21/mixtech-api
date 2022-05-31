package io.alexdo.mixtech.application.domain.exception;

public class NoUserAccessTokenException extends RuntimeException {
    public NoUserAccessTokenException(String message) {
        super(message);
    }
}
