package io.alexdo.mixtech.application.service;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;

import java.util.List;

public interface SongService {
    List<Song> getAllByQuery(String query);
    List<Song> getAllByNameInPage(String name, int page, int size);
    List<Song> getAllByAudioFeatures(AdvanceSearchRequest request);
}
