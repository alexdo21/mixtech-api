package io.alexdo.mixtech.application.domain.exception;

public class BothCompleteMatchSongsInPlaylistException extends RuntimeException {
    public BothCompleteMatchSongsInPlaylistException(String message) {
        super(message);
    }
}
