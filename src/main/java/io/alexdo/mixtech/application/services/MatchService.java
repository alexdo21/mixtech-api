package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MatchService {
    Page<MatchEntity> getAllByPage(int page, int size);
    List<MatchEntity> getAll();
    void create(MatchEntity matchEntity);
    void create(String spotifyUri2, Long uid);
    void remove(Long mid);
    int addSong(String spotifyUri2, Long mid, Long uid);
    MatchEntity getByMid(Long mid);
    List<DisplayMatchResponse> displayMatchByUid(Long uid);
    List<DisplayMatchResponse> displayCompleteMatchByUid(Long uid);
    List<DisplayMatchResponse> displayIncompleteMatchByUid(Long uid);
    List<DisplayMatchResponse> displayMatchBySnmae(String sname);
}
