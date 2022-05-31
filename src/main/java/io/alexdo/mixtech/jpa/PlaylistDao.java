package io.alexdo.mixtech.jpa;

import io.alexdo.mixtech.application.domain.Playlist;

import java.util.Optional;

public interface PlaylistDao {
    Optional<Playlist> findById(Long pid);
    Optional<Playlist> save(Playlist playlist);
    void deleteById(Long pid);
}
