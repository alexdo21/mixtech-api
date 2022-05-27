package io.alexdo.mixtech.application.domain.exception;

public class JpaUnableToSaveException extends RuntimeException {
    public JpaUnableToSaveException(String message) {
        super(message);
    }
}
