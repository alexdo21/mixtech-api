package io.alexdo.mixtech.application.service.impl;

import io.alexdo.mixtech.application.domain.Creates;
import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.application.domain.exception.*;
import io.alexdo.mixtech.jpa.dao.CreatesDao;
import io.alexdo.mixtech.jpa.dao.MatchDao;
import io.alexdo.mixtech.application.domain.MatchDisplay;
import io.alexdo.mixtech.application.service.MatchService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchDao matchDao;
    private final CreatesDao createsDao;

    @Override
    public List<MatchDisplay> getCompleteMatchesByUid(Long uid) {
        return matchDao.findCompleteMatchesByUid(uid).orElseThrow(() -> new ResourceNotFoundException("Complete matches not found"));
    }

    @Override
    public List<MatchDisplay> getIncompleteMatchesByUid(Long uid) {
        return matchDao.findIncompleteMatchesByUid(uid).orElseThrow(() -> new ResourceNotFoundException("Incomplete matches not found"));
    }

    @Override
    public void create(Long uid, String songId1) {
        Match newMatch = matchDao.save(Match.builder().sid1(songId1).build()).orElseThrow(() -> new JpaUnableToSaveException("Server encountered error saving new match"));
        createsDao.save(Creates.builder().uid(uid).mid(newMatch.getId()).build());
    }

    @Override
    public void pair(Long uid, Long mid, String sid2) {
        Match match = matchDao.findById(mid).orElseThrow(() -> new ResourceNotFoundException("Match not found for mid: " + mid));
        String sid1 = match.getSid1();
        if (sid1.equals(sid2)) {
            throw new MatchDuplicateSongException(String.format("Could not pair %s with %s for match %s, they are the same", sid1, sid2, mid));
        }
        if (matchDao.findByUidAndSongIds(uid, sid1, sid2).isPresent() || matchDao.findByUidAndSongIds(uid, sid2, sid1).isPresent()) {
            throw new MatchDuplicateMatchException(String.format("Match with %s and %s already exists for user: %s", sid1, sid2, uid));
        }
        matchDao.pair(mid, sid2);
    }

    @Override
    public void deleteByUidAndMid(Long uid, Long mid) {
        createsDao.deleteById(uid, mid);
        matchDao.deleteById(mid);
    }

    @Override
    public List<MatchDisplay> getAllBySongName(String songName) {
        return matchDao.findAllBySongName("%" + songName + "%").orElseThrow(() -> new ResourceNotFoundException("No matches found with song name: " + songName));
    }
}
