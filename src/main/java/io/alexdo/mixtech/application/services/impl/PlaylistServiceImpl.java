package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.CuratesDao;
import io.alexdo.mixtech.jpa.dao.PlaylistDao;
import io.alexdo.mixtech.jpa.dao.StoredProcedureDao;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.application.services.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistDao playlistDao;
    private final CuratesDao curatesDao;
    private final StoredProcedureDao storedProcedureDao;

    @Override
    public PlaylistEntity getByPid(Long pid) {
        return playlistDao.findByPid(pid);
    }

    @Override
    public boolean privacy(Long uid, Long pid, int privacy) {
        // If not the creator, than operation denied.
        if (curatesDao.findOneByUidAndPid(uid, pid) == null) {
            return false;
        }
        storedProcedureDao.updatePlaylistPrivacy(pid, privacy);
        return true;
    }

    @Override
    public void create(Long uid, PlaylistEntity playlistEntity) {
        storedProcedureDao.createPlaylist(uid, playlistEntity);
    }

    @Override
    public boolean remove(Long uid, Long pid) {
        if (curatesDao.findOneByUidAndPid(uid, pid) == null) {
            return false;
        }
        storedProcedureDao.removePlaylist(uid, pid);
        return true;
    }
}
