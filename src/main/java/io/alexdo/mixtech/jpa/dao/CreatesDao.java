package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.application.domain.Creates;

public interface CreatesDao {
    void save(Creates creates);
    void deleteById(Long uid, Long mid);
}
