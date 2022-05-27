package io.alexdo.mixtech.jpa.repository.custom.impl;

import io.alexdo.mixtech.application.domain.Playlist;
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
    public void createPlaylist(Long uid, Playlist playlist) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("create_playlist");
        storedProcedureQuery.registerStoredProcedureParameter(1, Long.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
        storedProcedureQuery.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);

        storedProcedureQuery.setParameter(1, uid);
        storedProcedureQuery.setParameter(2, playlist.getName());
        storedProcedureQuery.setParameter(4, playlist.getDescription());

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
}
