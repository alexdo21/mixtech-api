package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;

import java.util.List;

public interface CreatesDao {
    void save(CreatesEntity createsEntity);
    void deleteById(CreatesKey key);
    List<MatchEntity> findAllMatchesByUserId(Long uid);
}
