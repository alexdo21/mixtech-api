package io.alexdo.mixtech.jpa;

import io.alexdo.mixtech.application.domain.Match;

import java.util.List;
import java.util.Optional;

public interface MatchDao {
    Optional<List<Match>> findCompleteByMids(List<Long> mids);
    Optional<List<Match>> findIncompleteByMids(List<Long> mids);
    Optional<Match> save(Match match);
    void pair(Long mid, String sid2);
    Optional<Match> findById(Long mid);
    Optional<Match> findByUidAndSongIds(Long uid, String sid1, String sid2);
    void deleteById(Long mid);
    Optional<List<Match>> findAllBySongName(String songName);
}
