package io.alexdo.mixtech.jpa;

import io.alexdo.mixtech.application.domain.Creates;

import java.util.List;
import java.util.Optional;

public interface CreatesDao {
    Optional<List<Creates>> findCreatesByUid(Long uid);
    void save(Creates creates);
    void deleteById(Long uid, Long mid);
}
