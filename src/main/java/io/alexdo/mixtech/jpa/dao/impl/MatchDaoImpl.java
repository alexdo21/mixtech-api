package io.alexdo.mixtech.jpa.dao.impl;

import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.jpa.dao.MatchDao;
import io.alexdo.mixtech.application.domain.MatchDisplay;
import io.alexdo.mixtech.jpa.mapping.JpaMatchMapper;
import io.alexdo.mixtech.jpa.repository.MatchRepository;
import io.alexdo.mixtech.jpa.repository.custom.MatchCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchDaoImpl implements MatchDao {
    private final MatchRepository matchRepository;
    private final MatchCustomRepository matchCustomRepository;
    private final JpaMatchMapper jpaMatchMapper;

    @Override
    public Optional<List<MatchDisplay>> findCompleteMatchesByUid(Long uid) {
        return Optional.ofNullable(matchCustomRepository.findCompleteMatchesByUid(uid));
    }

    @Override
    public Optional<List<MatchDisplay>> findIncompleteMatchesByUid(Long uid) {
        return Optional.ofNullable(matchCustomRepository.findIncompleteMatchesByUid(uid));
    }

    @Override
    public Optional<Match> save(Match match) {
        return Optional.ofNullable(jpaMatchMapper.jpaMatchToMatch(matchRepository.save(jpaMatchMapper.matchToJpaMatch(match))));
    }

    @Override
    public void pair(Long mid, String sid2) {
        matchCustomRepository.pair(mid, sid2);
    }

    @Override
    public Optional<Match> findById(Long mid) {
        return matchRepository.findById(mid).map(jpaMatchMapper::jpaMatchToMatch);
    }

    @Override
    public Optional<Match> findByUidAndSongIds(Long uid, String sid1, String sid2) {
        return Optional.ofNullable(jpaMatchMapper.jpaMatchToMatch(matchCustomRepository.findByUidAndSongIds(uid, sid1, sid2)));
    }

    @Override
    public void deleteById(Long mid) {
        matchRepository.deleteById(mid);
    }

    @Override
    public Optional<List<MatchDisplay>> findAllBySongName(String songName) {
        return Optional.ofNullable(matchCustomRepository.findAllBySongName(songName));
    }
}
