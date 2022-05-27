package io.alexdo.mixtech.application.service.impl;

import io.alexdo.mixtech.application.service.SpotifyService;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.spotify.SpotifyOAuth2Client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpotifyServiceImpl implements SpotifyService {
    private final SpotifyOAuth2Client spotifyOAuth2Client;

    @Override
    public String requestAuthorizationCode() {
        Logger.logInfo("Requesting authorization code", this);
        return spotifyOAuth2Client.requestAuthorizationCode();
    }

    @Override
    public String requestAccessToken(String authorizationCode) {
        Logger.logInfo("Requesting access token", this);
        return spotifyOAuth2Client.requestAccessToken(authorizationCode);
    }
}
