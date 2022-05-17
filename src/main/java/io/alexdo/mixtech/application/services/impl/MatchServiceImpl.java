package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.CreatesDao;
import io.alexdo.mixtech.jpa.dao.MatchDao;
import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import io.alexdo.mixtech.application.services.MatchService;
import io.alexdo.mixtech.api.infrastructure.security.utils.SystemConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service related to matches
 */
@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {
    private final MatchDao matchDao;
    private final CreatesDao createsDao;

    @Override
    public Page<MatchEntity> getAllByPage(int page, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "mid");
        Pageable pageable = PageRequest.of(page, size, sort);
        return matchDao.findAll(pageable);
    }

    @Override
    public List<MatchEntity> getAll() {
        return matchDao.findAll();
    }

    @Override
    public void create(MatchEntity matchEntity) {
        matchDao.save(matchEntity);
    }

    @Override
    public void create(String spotifyUri1, Long uid) {
        Long mid = matchDao.save(new MatchEntity(spotifyUri1)).getMid();
        createsDao.save(new CreatesEntity(uid, mid));
    }

    @Override
    public void remove(Long mid) {
        matchDao.deleteById(mid);
    }

    @Override
    public int addSong(String spotifyUri2, Long mid, Long uid)
    {
        String spotifyUri1 = matchDao.findByMid(mid).getSpotifyUri1();
        if (spotifyUri1.equals(spotifyUri2)) { return SystemConstant.RET_ERR_DUPSONG; }
        if (matchDao.getMatchBySongs(spotifyUri1, spotifyUri2, uid)!=null
                || matchDao.getMatchBySongs(spotifyUri2, spotifyUri1, uid)!=null ) {
            return SystemConstant.RET_ERR_DUPMATCH;
        }
        matchDao.addSongTwo(spotifyUri2, mid);
        return SystemConstant.RET_SUC;
    }

    @Override
    public MatchEntity getByMid(Long mid) {
        return matchDao.findByMid(mid);
    }

    @Override
    public List<DisplayMatchResponse> displayMatchByUid(Long uid) {
        return matchDao.displayMatch(uid);
    }

    /**
     * find all complete matches a user has
     * @param uid user id
     * @return list of match-name pairs
     */
    @Override
    public List<DisplayMatchResponse> displayCompleteMatchByUid(Long uid) {
        return matchDao.displayCompleteMatch(uid);
    }

    @Override
    public List<DisplayMatchResponse> displayIncompleteMatchByUid(Long uid) {
        return matchDao.displayIncompleteMatch(uid);
    }

    @Override
    public List<DisplayMatchResponse> displayMatchBySnmae(String sname) {
        return matchDao.displayAllMatchBySname("%" + sname + "%");
    }
}
