package io.alexdo.mixtech.jpa.impl;

import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.jpa.PlaylistDao;
import io.alexdo.mixtech.jpa.mapping.JpaPlaylistMapper;
import io.alexdo.mixtech.jpa.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaylistDaoImpl implements PlaylistDao {
    private final PlaylistRepository playlistRepository;
    private final JpaPlaylistMapper jpaPlaylistMapper;

    @Override
    public Optional<Playlist> findById(Long pid) {
        return Optional.ofNullable(jpaPlaylistMapper.jpaPlaylistToPlaylist(playlistRepository.findById(pid).orElse(null)));
    }

    @Override
    public Optional<Playlist> save(Playlist playlist) {
        return Optional.ofNullable(jpaPlaylistMapper.jpaPlaylistToPlaylist(playlistRepository.save(jpaPlaylistMapper.playlistToJpaPlaylist(playlist))));
    }

    @Override
    public void deleteById(Long pid) {
        playlistRepository.deleteById(pid);
    }
}
