package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, String> {
    List<SongEntity> findByNameLike(String name, Sort sort);
    SongEntity findBySpotifyId(String spotifyId);
    List<SongEntity> findByNameLike(String name, Pageable pageable);
}
