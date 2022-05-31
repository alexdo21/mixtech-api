package io.alexdo.mixtech.application;

import io.alexdo.mixtech.application.domain.Match;

import java.util.List;

public interface MatchService {
    List<Match> getCompleteByUid(Long uid);
    List<Match> getIncompleteByUid(Long uid);
    void create(Long uid, String sid1);
    void pair(Long uid, Long mid, String sid2);
    void deleteByUidAndMid(Long uid, Long mid);
    List<Match> getCompleteBySongName(String songName);
    void addCompleteToPlaylist(Long mid, Long pid);
}
