package io.alexdo.mixtech.application;

import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.application.domain.Song;

import java.util.List;

public interface PlaylistService {
    void create(Long uid, Playlist playlist);
    List<Playlist> getAllByUid(Long uid);
    List<Song> getAllSongsByPid(Long pid);
    void addSong(Long pid, String sid);
    void deleteSong(Long pid, String sid);
    void deleteByUidAndPid(Long uid, Long pid);
    void addToSpotify(String spotifyId, Long pid);
}
