package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

import java.util.List;

public interface FollowsService {
    List<PlaylistEntity> getAllByUid(Long uid);
    boolean follow(Long pid, Long uid);
    boolean unfollow(Long pid, Long uid);
    void unfollow(Long pid);
}
