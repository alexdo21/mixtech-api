package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.application.domain.Playlist;

import java.util.Optional;

public interface PlaylistDao {
    Optional<Playlist> save(Playlist playlist);
    void deleteById(Long pid);
}
