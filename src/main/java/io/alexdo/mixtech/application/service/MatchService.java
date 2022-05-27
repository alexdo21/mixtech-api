package io.alexdo.mixtech.application.service;

import io.alexdo.mixtech.application.domain.MatchDisplay;

import java.util.List;

public interface MatchService {
    List<MatchDisplay> getCompleteMatchesByUid(Long uid);
    List<MatchDisplay> getIncompleteMatchesByUid(Long uid);
    void create(Long uid, String sid1);
    void pair(Long uid, Long mid, String sid2);
    void deleteByUidAndMid(Long uid, Long mid);
    List<MatchDisplay> getAllBySongName(String songName);
}
