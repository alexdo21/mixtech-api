package io.alexdo.mixtech.application.service;

public interface SpotifyService {
    String requestAuthorizationCode();
    String requestAccessToken(String authorizationCode);
}
