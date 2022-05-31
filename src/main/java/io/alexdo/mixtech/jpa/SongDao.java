package io.alexdo.mixtech.jpa;

import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.domain.Song;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SongDao {
    Optional<Song> findBySpotifyId(String spotifyId);
    Optional<List<Song>> findByNameLike(String name);
    Optional<List<Song>> findByNameLike(String name, Pageable pageable);
    Optional<List<Song>> findAllByAudioFeatures(AdvanceSearchRequest advanceSearchRequest);
    void save(Song song);
}
