package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MatchDao {
    List<MatchEntity> findAll();
    Page<MatchEntity> findAll(Pageable pageable);
    MatchEntity save(MatchEntity matchEntity);
    void deleteById(Long mid);
    MatchEntity findBySpotifyUri1AndSpotifyUri2(String spotfiyUri1, String spotifyUri2);
    MatchEntity findByMid(Long mid);
    List<DisplayMatchResponse> displayMatch(Long uid);
    void addSongTwo(String spotifyUri2, Long mid);
    List<DisplayMatchResponse> displayCompleteMatch(Long uid);
    List<DisplayMatchResponse> displayIncompleteMatch(Long uid);
    List<DisplayMatchResponse> displayAllMatchBySname(String sname);
    MatchEntity getMatchBySongs(String spotifyUri1, String spotifyUri2, Long uid);
}
