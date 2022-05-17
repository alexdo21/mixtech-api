package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.CreatesDao;
import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import io.alexdo.mixtech.application.services.CreatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatesServiceImpl implements CreatesService {
    private final CreatesDao createsDao;

    @Override
    public void create(CreatesEntity createsEntity) {
        createsDao.save(createsEntity);
    }

    @Override
    public void remove(CreatesKey key) {
        createsDao.deleteById(key);
    }

    @Override
    public List<MatchEntity> getAllByUid(Long uid) {
        return createsDao.findAllMatchesByUserId(uid);
    }
}
