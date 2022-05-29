package io.alexdo.mixtech.application.service.impl;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.jpa.dao.SongDao;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.service.SongService;
import io.alexdo.mixtech.spotify.SpotifySearchClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongDao songDao;
    private final SpotifySearchClient spotifySearchClient;

    @Override
    public List<Song> getAllByQuery(String query) {
        List<Song> mixTechSongs = songDao.findByNameLike("%" + query + "%", Sort.by(Sort.Direction.DESC, "popularity")).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name: " + query));
        List<Song> spotifySongs;
        try {
            spotifySongs = spotifySearchClient.searchTracks(query);
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new SpotifyException("Spotify exception occurred with message: " + e.getMessage());
        }
        List<Song> songs = new ArrayList<>(Stream.of(mixTechSongs, spotifySongs)
                .flatMap(Collection::stream)
                .distinct().toList());
        songs.sort(Comparator.comparing(Song::getPopularity).reversed());
        return songs;
    }

    @Override
    public List<Song> getAllByNameInPage(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        Pageable pageable = PageRequest.of(page, size, sort);
        return songDao.findByNameLike("%" + name + "%", pageable).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name like: " + name));
    }

    @Override
    public List<Song> getAllByAudioFeatures(AdvanceSearchRequest advanceSearchRequest) {
        return songDao.findAllByAudioFeatures(advanceSearchRequest).orElseThrow(() -> new ResourceNotFoundException("Song not found for audio feature query of: " + advanceSearchRequest));
    }
}
