package io.alexdo.mixtech.application.domain.exception;

public class MatchDuplicateMatchException extends RuntimeException {
    public MatchDuplicateMatchException(String message) {
        super(message);
    }
}
