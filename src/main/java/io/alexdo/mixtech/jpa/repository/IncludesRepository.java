package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface IncludesRepository extends JpaRepository<IncludesEntity, IncludesKey> {
    IncludesEntity findByPidAndSid(Long pid, String sid);
    @Transactional
    void deleteByPid(Long pid);
}
