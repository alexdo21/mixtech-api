package io.alexdo.mixtech.application.service;

import io.alexdo.mixtech.application.domain.User;

public interface UserService {
    void create(User user);
    User getBySpotifyId(String spotifyId);
}
