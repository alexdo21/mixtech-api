package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.repository.custom.StoredProcedureRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Repository
public class StoredProcedureRepositoryImpl implements StoredProcedureRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createPlaylist(Long uid, PlaylistEntity playlistEntity) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("create_playlist");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, uid);
        storedProcedureQuery.setParameter(2, playlistEntity.getPname());
        storedProcedureQuery.setParameter(3, playlistEntity.getPrivacy());
        storedProcedureQuery.setParameter(4, playlistEntity.getDescription());

        storedProcedureQuery.execute();
    }

    @Override
    public void removePlaylist(Long uid, Long pid) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("remove_playlist");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Long.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, uid);
        storedProcedureQuery.setParameter(2, pid);

        storedProcedureQuery.execute();
    }

    @Override
    public void updatePlaylistPrivacy(Long pid, Integer privacy) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("change_privacy");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, pid);
        storedProcedureQuery.setParameter(2, privacy);

        storedProcedureQuery.execute();
    }
}
