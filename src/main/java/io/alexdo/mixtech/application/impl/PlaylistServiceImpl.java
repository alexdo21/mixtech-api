package io.alexdo.mixtech.application.impl;

import io.alexdo.mixtech.application.domain.Curates;
import io.alexdo.mixtech.application.domain.Includes;
import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.JpaUnableToSaveException;
import io.alexdo.mixtech.application.domain.exception.PlaylistDuplicateSongException;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.application.SongService;
import io.alexdo.mixtech.jpa.CuratesDao;
import io.alexdo.mixtech.jpa.IncludesDao;
import io.alexdo.mixtech.jpa.PlaylistDao;
import io.alexdo.mixtech.application.PlaylistService;
import io.alexdo.mixtech.spotify.SpotifyPlaylistClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistDao playlistDao;
    private final IncludesDao includesDao;
    private final CuratesDao curatesDao;
    private final SongService songService;
    private final SpotifyPlaylistClient spotifyPlaylistClient;

    @Override
    @Transactional
    public void create(Long uid, Playlist playlist) {
        Playlist newPlaylist = playlistDao.save(playlist).orElseThrow(() -> new JpaUnableToSaveException("Server encountered error saving new playlist"));
        curatesDao.save(Curates.builder().uid(uid).pid(newPlaylist.getId()).build());
    }

    @Override
    public List<Playlist> getAllByUid(Long uid) {
        return curatesDao.findAllByUid(uid).orElseThrow(() -> new ResourceNotFoundException("Playlists not found"));
    }

    @Override
    public List<Song> getAllSongsByPid(Long pid) {
        return includesDao.findAllSongsByPid(pid).orElseThrow(() -> new ResourceNotFoundException("No songs found for playlist: " + pid));
    }

    @Override
    @Transactional
    public void addSong(Long pid, String sid) {
        if (includesDao.findByPidAndSid(pid, sid).isPresent()) {
            throw new PlaylistDuplicateSongException(String.format("Song with %s already exists for Playlist %s", sid, pid));
        }
        songService.saveBySpotifyIdIfDoesNotExist(sid);
        includesDao.save(Includes.builder().pid(pid).sid(sid).build());
    }

    @Override
    public void deleteSong(Long pid, String sid) {
        includesDao.deleteById(pid, sid);
    }

    @Override
    @Transactional
    public void deleteByUidAndPid(Long uid, Long pid) {
        curatesDao.deleteById(uid, pid);
        includesDao.deleteByPid(pid);
        playlistDao.deleteById(pid);
    }

    @Override
    @Transactional
    public void addToSpotify(String spotifyId, Long pid) {
        Playlist playlist = playlistDao.findById(pid).orElseThrow(() -> new ResourceNotFoundException("Playlist not found for id: " + pid));
        List<Song> songs = getAllSongsByPid(pid);
        try {
            if (Objects.isNull(playlist.getSpotifyId())) {
                spotifyPlaylistClient.createPlaylistWithSongs(spotifyId, playlist, songs);
            } else {
                spotifyPlaylistClient.addNewSongsToExistingPlaylist(playlist, songs);
            }
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
    }
}