package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.StoredProcedureDao;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.repository.custom.StoredProcedureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoredProcedureDaoImpl implements StoredProcedureDao {
    private final StoredProcedureRepository storedProcedureRepository;

    @Override
    public void createPlaylist(Long uid, PlaylistEntity playlistEntity) {
        storedProcedureRepository.createPlaylist(uid, playlistEntity);
    }

    @Override
    public void removePlaylist(Long uid, Long pid) {
        storedProcedureRepository.removePlaylist(uid, pid);
    }

    @Override
    public void updatePlaylistPrivacy(Long pid, Integer privacy) {
        storedProcedureRepository.updatePlaylistPrivacy(pid, privacy);
    }
}
