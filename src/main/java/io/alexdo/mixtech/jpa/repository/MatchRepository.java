package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<MatchEntity, Long> {
    MatchEntity findBySpotifyUri1AndSpotifyUri2(String spotify_uri1, String spotify_uri2);
    MatchEntity findByMid(Long mid);
}
