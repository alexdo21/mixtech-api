package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.PlaylistDao;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.repository.PlaylistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistDaoImpl implements PlaylistDao {
    private final PlaylistRepository playlistRepository;
    @Override
    public PlaylistEntity findByPid(Long pid) {
        return playlistRepository.findByPid(pid);
    }
}
