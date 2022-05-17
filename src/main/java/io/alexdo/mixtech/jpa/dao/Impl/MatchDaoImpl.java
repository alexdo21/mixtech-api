package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.MatchDao;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import io.alexdo.mixtech.jpa.repository.MatchRepository;
import io.alexdo.mixtech.jpa.repository.custom.MatchCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchDaoImpl implements MatchDao {
    private final MatchRepository matchRepository;
    private final MatchCustomRepository matchCustomRepository;

    @Override
    public List<MatchEntity> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Page<MatchEntity> findAll(Pageable pageable) {
        return matchRepository.findAll(pageable);
    }

    @Override
    public MatchEntity save(MatchEntity matchEntity) {
        return matchRepository.save(matchEntity);
    }

    @Override
    public void deleteById(Long mid) {
        matchRepository.deleteById(mid);
    }

    @Override
    public MatchEntity findBySpotifyUri1AndSpotifyUri2(String spotfiyUri1, String spotifyUri2) {
        return matchRepository.findBySpotifyUri1AndSpotifyUri2(spotfiyUri1, spotifyUri2);
    }

    @Override
    public MatchEntity findByMid(Long mid) {
        return matchRepository.findByMid(mid);
    }

    @Override
    public List<DisplayMatchResponse> displayMatch(Long uid) {
        return matchCustomRepository.displayMatch(uid);
    }

    @Override
    public void addSongTwo(String spotifyUri2, Long mid) {
        matchCustomRepository.addSongTwo(spotifyUri2, mid);
    }

    @Override
    public List<DisplayMatchResponse> displayCompleteMatch(Long uid) {
        return matchCustomRepository.displayCompleteMatch(uid);
    }

    @Override
    public List<DisplayMatchResponse> displayIncompleteMatch(Long uid) {
        return matchCustomRepository.displayIncompleteMatch(uid);
    }

    @Override
    public List<DisplayMatchResponse> displayAllMatchBySname(String sname) {
        return matchCustomRepository.displayAllMatchBySname(sname);
    }

    @Override
    public MatchEntity getMatchBySongs(String spotifyUri1, String spotifyUri2, Long uid) {
        return matchCustomRepository.getMatchBySongs(spotifyUri1, spotifyUri2, uid);
    }
}
