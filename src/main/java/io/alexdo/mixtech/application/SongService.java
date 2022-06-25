package io.alexdo.mixtech.application;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;

import java.util.List;

public interface SongService {
    List<Song> getAllByQuery(String query);
    List<Song> getAllByAudioFeatures(AdvanceSearchRequest request);
    void saveBySpotifyIdIfDoesNotExist(String spotifyId);
    void start(String songId, String deviceId);
    void resume(String deviceId);
    void pause(String deviceId);
}
