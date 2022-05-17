package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<PlaylistEntity, Long> {
    PlaylistEntity findByPid(Long pid);
}
