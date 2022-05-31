package io.alexdo.mixtech.jpa.impl;

import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.jpa.MatchDao;
import io.alexdo.mixtech.jpa.mapping.JpaMatchMapper;
import io.alexdo.mixtech.jpa.repository.MatchRepository;
import io.alexdo.mixtech.jpa.repository.custom.MatchCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchDaoImpl implements MatchDao {
    private final MatchRepository matchRepository;
    private final MatchCustomRepository matchCustomRepository;
    private final JpaMatchMapper jpaMatchMapper;

    @Override
    public Optional<List<Match>> findCompleteByMids(List<Long> mids) {
        return Optional.ofNullable(jpaMatchMapper.jpaMatchesToMatches(mids.stream().map(matchRepository::findById).filter(Optional::isPresent).map(Optional::get).filter(matchEntity -> Objects.nonNull(matchEntity.getSid1()) && Objects.nonNull(matchEntity.getSid2())).toList()));
    }

    @Override
    public Optional<List<Match>> findIncompleteByMids(List<Long> mids) {
        return Optional.ofNullable(jpaMatchMapper.jpaMatchesToMatches(mids.stream().map(matchRepository::findById).filter(Optional::isPresent).map(Optional::get).filter(matchEntity -> Objects.nonNull(matchEntity.getSid1()) && Objects.isNull(matchEntity.getSid2())).toList()));
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
    public Optional<List<Match>> findAllBySongName(String songName) {
        return Optional.ofNullable(jpaMatchMapper.jpaMatchesToMatches(matchCustomRepository.findAllBySongName("%" + songName + "%")));
    }
}
