package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.repository.custom.CuratesCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CuratesCustomRepositoryImpl implements CuratesCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<PlaylistEntity> findAllByUid(Long uid) {
        return entityManager.createNativeQuery("SELECT p.* FROM curates c, playlists p " + "WHERE c.uid=?1 AND c.pid = p.pid", PlaylistEntity.class).setParameter(1, uid).getResultList();
    }
}
