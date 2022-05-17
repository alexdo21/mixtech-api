package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;

public interface PlaylistDao {
    PlaylistEntity findByPid(Long pid);
}
