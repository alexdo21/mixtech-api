package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.SongEntity;

import java.util.List;

public interface PlaylistSongService {
    List<SongEntity> getAllByPid(Long pid);
    void add(String spotifyUri, Long pid);
    void remove(String spotifyUri, Long pid);
}
