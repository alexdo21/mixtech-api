package io.alexdo.mixtech.application.impl;

import io.alexdo.mixtech.api.infrastructure.security.cryptography.TokenProvider;
import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.jpa.UserDao;
import io.alexdo.mixtech.application.UserService;
import io.alexdo.mixtech.spotify.SpotifyAuthorizationClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final SpotifyAuthorizationClient spotifyAuthorizationClient;
    private final TokenProvider tokenProvider;

    @Override
    public void create(User user) {
        Logger.logInfo(String.format("Creating user for %s", user.getSpotifyId()), this);
        if (userDao.findByEmail(user.getEmail()).isEmpty()) {
            userDao.save(user);
        }
    }

    @Override
    public User getBySpotifyId(String spotifyId) throws UserDoesNotExistException {
        return userDao.findBySpotifyId(spotifyId).orElseThrow(() -> new UserDoesNotExistException("User does not exist"));
    }

    @Override
    public String getUserAccessToken() {
        Logger.logInfo("Getting user access token", this);
        return spotifyAuthorizationClient.getAccessToken();
    }

    @Override
    public String getRefreshToken(Authentication authentication) {
        Logger.logInfo("Getting user refresh token", this);
        String token = tokenProvider.createToken(authentication);
        try {
            spotifyAuthorizationClient.refreshToken();
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
        return token;
    }
}
