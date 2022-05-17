package io.alexdo.mixtech.jpa.repository.custom;

import io.alexdo.mixtech.jpa.entity.MatchEntity;

import java.util.List;

public interface CreatesCustomRepository {
    List<MatchEntity> findAllMatchesByUserId(Long uid);
}
