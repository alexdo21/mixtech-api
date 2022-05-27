package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.application.domain.User;

import java.util.Optional;

public interface UserDao {
    void save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findBySpotifyId(String spotifyId);
}
