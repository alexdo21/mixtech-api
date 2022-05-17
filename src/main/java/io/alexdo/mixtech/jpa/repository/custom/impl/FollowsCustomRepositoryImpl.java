package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.repository.custom.FollowsCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FollowsCustomRepositoryImpl implements FollowsCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PlaylistEntity> getAllByUid(Long uid) {
        return entityManager.createNativeQuery("select p.* from follows f, playlists p " + "where f.pid = p.pid and f.uid = ?1", PlaylistEntity.class).setParameter(1, uid).getResultList();
    }
}
