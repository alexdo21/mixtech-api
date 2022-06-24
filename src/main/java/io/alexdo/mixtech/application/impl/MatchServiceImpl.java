package io.alexdo.mixtech.application.impl;

import io.alexdo.mixtech.application.domain.*;
import io.alexdo.mixtech.application.domain.exception.*;
import io.alexdo.mixtech.application.SongService;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.jpa.CreatesDao;
import io.alexdo.mixtech.jpa.IncludesDao;
import io.alexdo.mixtech.jpa.MatchDao;
import io.alexdo.mixtech.application.MatchService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchDao matchDao;
    private final CreatesDao createsDao;
    private final SongService songService;

    @Override
    @Transactional
    public List<Match> getCompleteByUid(Long uid) {
        Logger.logInfo(String.format("Getting complete matches for %d", uid), this);
        List<Creates> createsList = createsDao.findCreatesByUid(uid).orElseThrow(() -> new ResourceNotFoundException("Matches not found for user " + uid));
        return matchDao.findCompleteByMids(createsList.stream().map(Creates::getMid).toList()).orElseThrow(() -> new ResourceNotFoundException("Complete matches not found"));
    }

    @Override
    @Transactional
    public List<Match> getIncompleteByUid(Long uid) {
        Logger.logInfo(String.format("Getting incomplete matches for %d", uid), this);
        List<Creates> createsList = createsDao.findCreatesByUid(uid).orElseThrow(() -> new ResourceNotFoundException("Matches not found for user " + uid));
        return matchDao.findIncompleteByMids(createsList.stream().map(Creates::getMid).toList()).orElseThrow(() -> new ResourceNotFoundException("Incomplete matches not found"));
    }

    @Override
    @Transactional
    public void create(Long uid, String sid1) {
        Logger.logInfo(String.format("Creating match with %s", sid1), this);
        songService.saveBySpotifyIdIfDoesNotExist(sid1);
        Match newMatch = matchDao.save(Match.builder().song1(Song.builder().spotifyId(sid1).build()).build()).orElseThrow(() -> new JpaUnableToSaveException("Server encountered error saving new match"));
        createsDao.save(Creates.builder().uid(uid).mid(newMatch.getId()).build());
    }

    @Override
    @Transactional
    public void pair(Long uid, Long mid, String sid2) {
        Match match = matchDao.findById(mid).orElseThrow(() -> new ResourceNotFoundException("Match not found for mid: " + mid));
        String sid1 = match.getSong1().getSpotifyId();
        Logger.logInfo(String.format("Pairing %s with %s", sid1, sid2), this);
        if (sid1.equals(sid2)) {
            throw new MatchDuplicateSongException(String.format("Could not pair %s with %s for match %s, they are the same", sid1, sid2, mid));
        }
        if (matchDao.findByUidAndSongIds(uid, sid1, sid2).isPresent() || matchDao.findByUidAndSongIds(uid, sid2, sid1).isPresent()) {
            throw new MatchDuplicateMatchException(String.format("Match with %s and %s already exists for user: %s", sid1, sid2, uid));
        }
        songService.saveBySpotifyIdIfDoesNotExist(sid2);
        matchDao.pair(mid, sid2);
    }

    @Override
    @Transactional
    public void deleteByUidAndMid(Long uid, Long mid) {
        Logger.logInfo(String.format("Deleting match %d", mid), this);
        createsDao.deleteById(uid, mid);
        matchDao.deleteById(mid);
    }

    @Override
    public List<Match> getCompleteBySongName(String songName) {
        Logger.logInfo(String.format("Getting complete matches for %s", songName), this);
        List<Match> matches = matchDao.findAllBySongName(songName).orElseThrow(() -> new ResourceNotFoundException("No matches found with song name: " + songName));
        return matches.stream().filter(match -> Objects.nonNull(match.getSong1()) && Objects.nonNull(match.getSong2())).toList();
    }
}
