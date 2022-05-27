package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.application.domain.Playlist;

public interface StoredProcedureRepository {
    void createPlaylist(Long uid, Playlist playlist);
    void removePlaylist(Long uid, Long pid);
}
