package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SongDao {
    List<SongEntity> findBySnameLike(String sname, Sort sort);
    SongEntity findBySpotifyID(String spotfiyID);
    List<SongEntity> findBySnameLike(String sname, Pageable pageable);
    List<SongEntity> findAllByAudioFeatures(String query);
}
