package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.repository.custom.SongCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class SongCustomRepositoryImpl implements SongCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SongEntity> findAllByAudioFeatures(String query) {
        System.out.println(query);
        return entityManager.createNativeQuery(query, SongEntity.class).getResultList();
    }
}
