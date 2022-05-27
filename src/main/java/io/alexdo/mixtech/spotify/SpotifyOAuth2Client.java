package io.alexdo.mixtech.spotify;

public interface SpotifyOAuth2Client {
    String requestAuthorizationCode();
    String requestAccessToken(String authorizationCode);
}
