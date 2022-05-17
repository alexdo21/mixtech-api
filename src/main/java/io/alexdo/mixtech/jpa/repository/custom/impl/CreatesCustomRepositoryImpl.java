package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.repository.custom.CreatesCustomRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CreatesCustomRepositoryImpl implements CreatesCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MatchEntity> findAllMatchesByUserId(Long uid) {
        return entityManager.createNativeQuery("SELECT m.* FROM creates c, matches m " + "WHERE c.uid=?1 AND c.mid = m.mid", MatchEntity.class).setParameter(1, uid).getResultList();
    }
}
