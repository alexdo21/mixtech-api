package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;

import java.util.List;

public interface SongService {
    List<SongEntity> getAllByName(String name);
    List<SongEntity> getAllByNameInPage(String name, int page, int size);
    List<SongEntity> getAllByAudioFeatures(AdvanceSearchRequest request);
    SongEntity getByID(String spotifyID);
}
