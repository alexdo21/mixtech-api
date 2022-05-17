package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.FollowsDao;
import io.alexdo.mixtech.jpa.entity.FollowsEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.FollowsKey;
import io.alexdo.mixtech.jpa.repository.FollowsRepository;
import io.alexdo.mixtech.jpa.repository.custom.FollowsCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowsDaoImpl implements FollowsDao {
    private final FollowsRepository followsRepository;
    private final FollowsCustomRepository followsCustomRepository;

    @Override
    public void save(FollowsEntity followsEntity) {
        followsRepository.save(followsEntity);
    }

    @Override
    public void deleteById(FollowsKey followsKey) {
        followsRepository.deleteById(followsKey);
    }

    @Override
    public void deleteAll(List<FollowsEntity> followsEntities) {
        followsRepository.deleteAll(followsEntities);
    }

    @Override
    public FollowsEntity findByPidAndUid(Long pid, Long uid) {
        return followsRepository.findByPidAndUid(pid, uid);
    }

    @Override
    public void updateAccessByPidAndUid(Long pid, Long uid, int access) {
        followsRepository.updateAccessByPidAndUid(pid, uid, access);
    }

    @Override
    public List<FollowsEntity> findAllByPid(Long pid) {
        return followsRepository.findAllByPid(pid);
    }

    @Override
    public List<PlaylistEntity> getAllByUid(Long uid) {
        return followsCustomRepository.getAllByUid(uid);
    }
}
