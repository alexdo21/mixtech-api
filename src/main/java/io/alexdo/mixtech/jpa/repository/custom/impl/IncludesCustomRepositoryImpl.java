package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.repository.custom.IncludesCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class IncludesCustomRepositoryImpl implements IncludesCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SongEntity> findAllSongsByPid(Long pid) {
        return entityManager.createNativeQuery("select s.* from songs s, includes i " + "where i.pid = ?1 and i.sid = s.spotify_id", SongEntity.class).setParameter(1, pid).getResultList();
    }
}
