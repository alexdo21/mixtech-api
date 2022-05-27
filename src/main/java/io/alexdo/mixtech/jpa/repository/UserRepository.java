package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
    UserEntity findBySpotifyId(String spotifyId);
}
