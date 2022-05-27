package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
}
