package io.alexdo.mixtech.application.impl;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.jpa.SongDao;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.SongService;
import io.alexdo.mixtech.spotify.SpotifyPlayerClient;
import io.alexdo.mixtech.spotify.SpotifySearchClient;
import io.alexdo.mixtech.spotify.SpotifyTrackClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongDao songDao;
    private final SpotifySearchClient spotifySearchClient;
    private final SpotifyTrackClient spotifyTrackClient;
    private final SpotifyPlayerClient spotifyPlayerClient;

    @Override
    public List<Song> getAllByQuery(String query) {
        Logger.logInfo(String.format("Searching by query: %s", query), this);
        List<Song> songs = songDao.findByNameLike(query).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name: " + query));
        try {
            songs.addAll(spotifySearchClient.searchTracks(query));
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
        return songs.stream().distinct().sorted(Comparator.comparing(Song::getPopularity).reversed()).toList();
    }

    @Override
    public List<Song> getAllByNameInPage(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        Pageable pageable = PageRequest.of(page, size, sort);
        return songDao.findByNameLike("%" + name + "%", pageable).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name like: " + name));
    }

    @Override
    public List<Song> getAllByAudioFeatures(AdvanceSearchRequest advanceSearchRequest) {
        Logger.logInfo(String.format("Searching by advanced query: %s", advanceSearchRequest.toString()), this);
        return songDao.findAllByAudioFeatures(advanceSearchRequest).orElseThrow(() -> new ResourceNotFoundException("Song not found for audio feature query of: " + advanceSearchRequest));
    }

    @Override
    public void saveBySpotifyIdIfDoesNotExist(String spotifyId) {
        Logger.logInfo(String.format("Saving song %s to MixTech", spotifyId), this);
        if (songDao.findBySpotifyId(spotifyId).isEmpty()) {
            try {
                songDao.save(spotifyTrackClient.getTrack(spotifyId));
            } catch (IOException | ParseException | SpotifyWebApiException e) {
                throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
            }
        }
    }

    @Override
    public void start(String songId, String deviceId) {
        Logger.logInfo("Starting song", this);
        try {
            spotifyPlayerClient.start(songId, deviceId);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
    }

    @Override
    public void resume(String deviceId) {
        Logger.logInfo("Resuming song", this);
        try {
            spotifyPlayerClient.resume(deviceId);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
    }

    @Override
    public void pause(String deviceId) {
        Logger.logInfo("Pausing song", this);
        try {
            spotifyPlayerClient.pause(deviceId);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
    }
}
