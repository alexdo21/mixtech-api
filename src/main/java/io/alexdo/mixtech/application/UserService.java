package io.alexdo.mixtech.application;

import io.alexdo.mixtech.application.domain.User;
import org.springframework.security.core.Authentication;

public interface UserService {
    void create(User user);
    User getBySpotifyId(String spotifyId);
    String getUserAccessToken();
    String getRefreshToken(Authentication authentication);
}
