package io.alexdo.mixtech.spotify.impl;

import io.alexdo.mixtech.spotify.SpotifyAuthorizationClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRefreshRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyAuthorizationClientImpl implements SpotifyAuthorizationClient {
    private final SpotifyApi spotifyApi;

    @Override
    public String getAccessToken() {
        return spotifyApi.getAccessToken();
    }

    @Override
    public void refreshToken() throws IOException, ParseException, SpotifyWebApiException {
        AuthorizationCodeRefreshRequest authorizationCodeRefreshRequest = spotifyApi.authorizationCodeRefresh().build();
        AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRefreshRequest.execute();
        spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
    }
}
