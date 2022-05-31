package io.alexdo.mixtech.jpa;

import io.alexdo.mixtech.application.domain.Curates;
import io.alexdo.mixtech.application.domain.Playlist;

import java.util.List;
import java.util.Optional;

public interface CuratesDao {
    void save(Curates curates);
    void deleteById(Long uid, Long pid);
    Optional<List<Playlist>> findAllByUid(Long uid);
}