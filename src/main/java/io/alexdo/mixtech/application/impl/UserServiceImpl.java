package io.alexdo.mixtech.application.impl;

import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.NoUserAccessTokenException;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.jpa.UserDao;
import io.alexdo.mixtech.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final SpotifyApi spotifyApi;

    @Override
    public void create(User user) {
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
        String accessToken = spotifyApi.getAccessToken();
        if (Objects.isNull(accessToken) || accessToken.isEmpty()) {
            throw new NoUserAccessTokenException("User access token not found");
        }
        return accessToken;
    }
}
