package io.alexdo.mixtech.spotify;

import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public interface SpotifyAuthorizationClient {
    String getAccessToken();
    void refreshToken() throws IOException, ParseException, SpotifyWebApiException;
}
