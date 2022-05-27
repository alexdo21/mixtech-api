package io.alexdo.mixtech.application.domain.exception;

public class PlaylistDuplicateSongException extends RuntimeException {
    public PlaylistDuplicateSongException(String message) {
        super(message);
    }
}
