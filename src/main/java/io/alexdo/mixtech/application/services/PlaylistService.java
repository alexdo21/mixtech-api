package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

/**
 * services related to playlists
 */

public interface PlaylistService {
//    List<PlaylistEntity> getAllByPage(Long uid, int page, int size);
    PlaylistEntity getByPid(Long pid);
    boolean privacy(Long uid, Long pid, int privacy);
    void create(Long uid, PlaylistEntity playlistEntity);
    boolean remove(Long uid, Long pid);
}
