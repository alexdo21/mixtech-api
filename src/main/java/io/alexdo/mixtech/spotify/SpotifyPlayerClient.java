package io.alexdo.mixtech.spotify;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public interface SpotifyPlayerClient {
    void start(String spotifyId, String deviceId) throws IOException, ParseException, SpotifyWebApiException;
    void resume(String deviceId) throws IOException, ParseException, SpotifyWebApiException;
    void pause(String deviceId) throws IOException, ParseException, SpotifyWebApiException;
}
