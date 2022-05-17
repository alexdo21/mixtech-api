package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchCustomRepository {
    List<DisplayMatchResponse> displayMatch(Long uid);
    @Transactional
    void addSongTwo(String spotifyUri2, Long mid);
    List<DisplayMatchResponse> displayCompleteMatch(Long uid);
    List<DisplayMatchResponse> displayIncompleteMatch(Long uid);
    List<DisplayMatchResponse> displayAllMatchBySname(String sname);
    MatchEntity getMatchBySongs(String spotifyUri1, String spotifyUri2, Long uid);
}
