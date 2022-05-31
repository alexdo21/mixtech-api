package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.repository.custom.MatchCustomRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MatchCustomRepositoryImpl implements MatchCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void pair(Long mid, String sid2) {
        entityManager.createNativeQuery("update matches set sid2 = ?1 where id = ?2")
                .setParameter(1, sid2)
                .setParameter(2, mid).executeUpdate();
    }

    @Override
    public MatchEntity findByUidAndSongIds(Long uid, String sid1, String sid2) {
        Query query = entityManager.createNativeQuery("select m.* from matches m, creates c " +
                        "where c.mid = m.id and m.sid1 = ?1 and m.sid2 = ?2 " +
                        "and c.uid = ?3", MatchEntity.class)
                .setParameter(1, sid1)
                .setParameter(2, sid2)
                .setParameter(3, uid);
        try {
            return (MatchEntity) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<MatchEntity> findAllBySongName(String songName) {
        return entityManager.createNativeQuery("select distinct m.* " +
                        "from matches m " +
                        "inner join songs s1 on m.sid1 = s1.spotify_id " +
                        "left join songs s2 on m.sid2 = s2.spotify_id " +
                        "where s1.name like ?1 or s2.name like ?1", MatchEntity.class)
                .setParameter(1, songName).getResultList();
    }
}
