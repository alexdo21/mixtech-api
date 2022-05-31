package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MatchCustomRepository {
    @Transactional
    void pair(Long mid, String sid2);
    MatchEntity findByUidAndSongIds(Long uid, String sid1, String sid2);
    List<MatchEntity> findAllBySongName(String songName);
}
