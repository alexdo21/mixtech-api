package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

import java.util.List;

public interface CuratesService {
    List<PlaylistEntity> getAllByUid(Long uid);
    void create(Long uid, Long pid);
    void remove(Long uid, Long pid);
}
