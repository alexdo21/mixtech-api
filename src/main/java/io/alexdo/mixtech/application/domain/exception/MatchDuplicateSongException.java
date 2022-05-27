package io.alexdo.mixtech.application.domain.exception;

public class MatchDuplicateSongException extends RuntimeException{
    public MatchDuplicateSongException(String message) {
        super(message);
    }
}
