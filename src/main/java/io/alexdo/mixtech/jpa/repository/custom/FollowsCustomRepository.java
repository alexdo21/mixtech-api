package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

import java.util.List;

public interface FollowsCustomRepository {
    List<PlaylistEntity> getAllByUid(Long uid);
}
