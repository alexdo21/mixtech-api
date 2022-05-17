package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncludesRepository extends JpaRepository<IncludesEntity, IncludesKey> {
    IncludesEntity findBySpotifyUriAndPid(String spotifyUri, Long pid);
}
