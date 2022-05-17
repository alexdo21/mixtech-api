package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;

import java.util.List;

public interface CreatesService {
    void create(CreatesEntity createsEntity);
    void remove(CreatesKey key);
    List<MatchEntity> getAllByUid(Long uid);
}
