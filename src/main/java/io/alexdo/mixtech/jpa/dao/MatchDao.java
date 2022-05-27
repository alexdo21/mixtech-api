package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.application.domain.MatchDisplay;

import java.util.List;
import java.util.Optional;

public interface MatchDao {
    Optional<List<MatchDisplay>> findCompleteMatchesByUid(Long uid);
    Optional<List<MatchDisplay>> findIncompleteMatchesByUid(Long uid);
    Optional<Match> save(Match match);
    void pair(Long mid, String sid2);
    Optional<Match> findById(Long mid);
    Optional<Match> findByUidAndSongIds(Long uid, String sid1, String sid2);
    void deleteById(Long mid);
    Optional<List<MatchDisplay>> findAllBySongName(String songName);
}
