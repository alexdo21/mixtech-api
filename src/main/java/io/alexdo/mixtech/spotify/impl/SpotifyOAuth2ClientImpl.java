package io.alexdo.mixtech.spotify.impl;

import io.alexdo.mixtech.spotify.SpotifyOAuth2Client;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.enums.AuthorizationScope;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.io.IOException;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class SpotifyOAuth2ClientImpl implements SpotifyOAuth2Client {
    private final SpotifyApi spotifyApi;

    @Override
    public String requestAuthorizationCode() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest =
                spotifyApi.authorizationCodeUri()
                        .scope(AuthorizationScope.USER_READ_EMAIL,
                                AuthorizationScope.USER_READ_PRIVATE,
                                AuthorizationScope.PLAYLIST_READ_COLLABORATIVE,
                                AuthorizationScope.PLAYLIST_MODIFY_PUBLIC,
                                AuthorizationScope.PLAYLIST_READ_PRIVATE,
                                AuthorizationScope.PLAYLIST_MODIFY_PRIVATE,
                                AuthorizationScope.USER_MODIFY_PLAYBACK_STATE,
                                AuthorizationScope.USER_READ_PLAYBACK_STATE,
                                AuthorizationScope.USER_READ_CURRENTLY_PLAYING)
                        .show_dialog(true)
                        .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

    @Override
    public String requestAccessToken(String authorizationCode) {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(authorizationCode).build();
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return spotifyApi.getAccessToken();
    }
}
