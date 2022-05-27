package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.application.domain.MatchDisplay;
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
    public List<MatchDisplay> findCompleteMatchesByUid(Long uid) {
        return entityManager.createQuery("select New io.alexdo.mixtech.application.domain.MatchDisplay(m.id, s1.name, s2.name)" +
                        " from MatchEntity m, SongEntity s1, SongEntity s2, CreatesEntity c " +
                        "where m.sid1 = s1.spotifyId and m.sid2 = s2.spotifyId " +
                        "and m.id = c.mid and c.uid = ?1", MatchDisplay.class)
                .setParameter(1, uid).getResultList();
    }

    @Override
    public List<MatchDisplay> findIncompleteMatchesByUid(Long uid) {
        return entityManager.createQuery("select New io.alexdo.mixtech.application.domain.MatchDisplay(m.id, s.name)" +
                        " from MatchEntity m, SongEntity s, CreatesEntity c " +
                        "where m.sid1 = s.spotifyId and m.sid2 IS NULL " +
                        "and m.id = c.mid and c.uid = ?1", MatchDisplay.class)
                .setParameter(1, uid).getResultList();
    }

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
    public List<MatchDisplay> findAllBySongName(String songName) {
        return entityManager.createQuery("select distinct New io.alexdo.mixtech.application.domain.MatchDisplay(m.id, s1.name, s2.name) " +
                        "from MatchEntity m " +
                        "inner join SongEntity s1 on m.sid1 = s1.spotifyId " +
                        "left join SongEntity s2 on m.sid2 = s2.spotifyId " +
                        "where s1.name like ?1 or s2.name like ?1", MatchDisplay.class)
                .setParameter(1, songName).getResultList();
    }
}
