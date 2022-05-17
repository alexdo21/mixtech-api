package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

public interface StoredProcedureDao {
    void createPlaylist(Long uid, PlaylistEntity playlistEntity);
    void removePlaylist(Long uid, Long pid);
    void updatePlaylistPrivacy(Long pid, Integer privacy);
}
