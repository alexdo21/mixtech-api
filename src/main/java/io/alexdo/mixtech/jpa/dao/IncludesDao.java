package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;

import java.util.List;

public interface IncludesDao {
    void save(IncludesEntity includesEntity);
    void deleteById(IncludesKey includesKey);
    IncludesEntity findBySpotifyUriAndPid(String spotifyUri, Long pid);
    List<SongEntity> findAllByPid(Long Pid);
}
