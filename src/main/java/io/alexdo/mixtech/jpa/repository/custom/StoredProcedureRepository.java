package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

public interface StoredProcedureRepository {
    void createPlaylist(Long uid, PlaylistEntity playlistEntity);
    void removePlaylist(Long uid, Long pid);
    void updatePlaylistPrivacy(Long pid, Integer privacy);
}
