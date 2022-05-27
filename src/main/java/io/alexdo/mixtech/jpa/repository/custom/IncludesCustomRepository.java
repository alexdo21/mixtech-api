package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.jpa.entity.SongEntity;

import java.util.List;

public interface IncludesCustomRepository {
    List<SongEntity> findAllSongsByPid(Long pid);
}
