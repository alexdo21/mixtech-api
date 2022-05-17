package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.CreatesDao;
import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import io.alexdo.mixtech.jpa.repository.CreatesRepository;
import io.alexdo.mixtech.jpa.repository.custom.CreatesCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreatesDaoImpl implements CreatesDao {
    private final CreatesRepository createsRepository;
    private final CreatesCustomRepository createsCustomRepository;

    @Override
    public void save(CreatesEntity createsEntity) {
        createsRepository.save(createsEntity);
    }

    @Override
    public void deleteById(CreatesKey key) {
        createsRepository.deleteById(key);
    }

    @Override
    public List<MatchEntity> findAllMatchesByUserId(Long uid) {
        return createsCustomRepository.findAllMatchesByUserId(uid);
    }
}
