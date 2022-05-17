package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
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
    public List<DisplayMatchResponse> displayMatch(Long uid) {
        return entityManager.createQuery("select New io.alexdo.mixtech..DisplayMatchResponse(m.mid, s1.sname, s2.sname)" +
                        " from MatchEntity m, SongEntity s1, SongEntity s2, CreatesEntity c " +
                        "where m.spotifyUri1 = s1.spotifyID and m.spotifyUri2 = s2.spotifyID " +
                        "and m.mid = c.mid and c.uid = ?1", DisplayMatchResponse.class)
                .setParameter(1, uid).getResultList();
    }

    @Override
    @Transactional
    public void addSongTwo(String spotifyUri2, Long mid) {
        entityManager.createNativeQuery("update matches set spotify_uri2 = ?1 where mid = ?2")
                .setParameter(1, spotifyUri2)
                .setParameter(2, mid).executeUpdate();
    }

    @Override
    public List<DisplayMatchResponse> displayCompleteMatch(Long uid) {
        return entityManager.createQuery("select New io.alexdo.mixtech.api.dto.DisplayMatchResponse(m.mid, s1.sname, s2.sname)" +
                        " from MatchEntity m, SongEntity s1, SongEntity s2, CreatesEntity c " +
                        "where m.spotifyUri1 = s1.spotifyID and m.spotifyUri2 = s2.spotifyID " +
                        "and m.mid = c.mid and c.uid = ?1", DisplayMatchResponse.class)
                .setParameter(1, uid).getResultList();
    }

    @Override
    public List<DisplayMatchResponse> displayIncompleteMatch(Long uid) {
        return entityManager.createQuery("select New io.alexdo.mixtech.api.dto.DisplayMatchResponse(m.mid, s.sname)" +
                        " from MatchEntity m, SongEntity s, CreatesEntity c " +
                        "where m.spotifyUri1 = s.spotifyID and m.spotifyUri2 IS NULL " +
                        "and m.mid = c.mid and c.uid = ?1", DisplayMatchResponse.class)
                .setParameter(1, uid).getResultList();
    }

    @Override
    public List<DisplayMatchResponse> displayAllMatchBySname(String sname) {
        return entityManager.createQuery("select distinct New io.alexdo.mixtech.api.dto.DisplayMatchResponse(m.mid, s1.sname, s2.sname)" +
                        " from MatchEntity m, SongEntity s1, SongEntity s2 " +
                        "where (s1.sname like ?1 or s2.sname like ?1) " +
                        "and m.spotifyUri1 = s1.spotifyID and m.spotifyUri2 = s2.spotifyID ", DisplayMatchResponse.class)
                .setParameter(1, sname).getResultList();
    }

    @Override
    public MatchEntity getMatchBySongs(String spotifyUri1, String spotifyUri2, Long uid) {
        Query query = entityManager.createNativeQuery("select m.* from matches m, creates c " +
                        "where c.mid = m.mid and m.spotify_uri1 = ?1 and m.spotify_uri2 = ?2 " +
                        "and c.uid = ?3", MatchEntity.class)
                .setParameter(1, spotifyUri1)
                .setParameter(2, spotifyUri2)
                .setParameter(3, uid);
        try {
            MatchEntity matchEntity = (MatchEntity) query.getSingleResult();
            return matchEntity;
        } catch (NoResultException e) {
            return null;
        }
    }
}
