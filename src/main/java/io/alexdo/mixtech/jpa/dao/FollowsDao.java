package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.FollowsEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.FollowsKey;

import java.util.List;

public interface FollowsDao {
    void save(FollowsEntity followsEntity);
    void deleteById(FollowsKey followsKey);
    void deleteAll(List<FollowsEntity> followsEntities);
    FollowsEntity findByPidAndUid(Long pid, Long uid);
    void updateAccessByPidAndUid(Long pid, Long uid, int access);
    List<FollowsEntity> findAllByPid(Long pid);
    List<PlaylistEntity> getAllByUid(Long uid);
}
