package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.CuratesDao;
import io.alexdo.mixtech.jpa.dao.FollowsDao;
import io.alexdo.mixtech.jpa.dao.PlaylistDao;
import io.alexdo.mixtech.jpa.entity.FollowsEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.FollowsKey;
import io.alexdo.mixtech.application.services.FollowsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowsService {
    private final FollowsDao followsDao;
    private final PlaylistDao playlistDao;
    private final CuratesDao curatesDao;

    @Override
    public List<PlaylistEntity> getAllByUid(Long uid) {
        return followsDao.getAllByUid(uid);
    }

    @Override
    public boolean follow(Long pid, Long uid) {
        // non-creator follow a playlist
        // playlist creator should automatically follow his/her playlist
        if ( (playlistDao.findByPid(pid)).getPrivacy() == 1 || curatesDao.findOneByUidAndPid(uid, pid) != null) {
            if (followsDao.findByPidAndUid(pid, uid) == null) {
                followsDao.save(new FollowsEntity(pid, uid, 1));
            } else {
                followsDao.updateAccessByPidAndUid(pid, uid, 1);
            }
            return true;
        }
        // user cannot follow a private playlist
        else {
            return false;
        }
    }

    @Override
    public boolean unfollow(Long pid, Long uid) {
        followsDao.deleteById(new FollowsKey(pid, uid));
        return true;
    }

    @Override
    public void unfollow(Long pid) {
        List<FollowsEntity> list = followsDao.findAllByPid(pid);
        followsDao.deleteAll(list);
    }
}
