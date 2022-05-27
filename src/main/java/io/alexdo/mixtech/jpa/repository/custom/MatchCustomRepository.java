package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.application.domain.MatchDisplay;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchCustomRepository {
    List<MatchDisplay> findCompleteMatchesByUid(Long uid);
    List<MatchDisplay> findIncompleteMatchesByUid(Long uid);
    @Transactional
    void pair(Long mid, String sid2);
    MatchEntity findByUidAndSongIds(Long uid, String sid1, String sid2);
    List<MatchDisplay> findAllBySongName(String songName);
}
