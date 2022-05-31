package io.alexdo.mixtech.jpa.impl;

import io.alexdo.mixtech.application.domain.Creates;
import io.alexdo.mixtech.jpa.CreatesDao;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import io.alexdo.mixtech.jpa.mapping.JpaCreatesMapper;
import io.alexdo.mixtech.jpa.repository.CreatesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreatesDaoImpl implements CreatesDao {
    private final CreatesRepository createsRepository;
    private final JpaCreatesMapper jpaCreatesMapper;

    @Override
    public Optional<List<Creates>> findCreatesByUid(Long uid) {
        return Optional.ofNullable(jpaCreatesMapper.jpaCreatesListToCreatesList(createsRepository.findByUid(uid)));
    }

    @Override
    public void save(Creates creates) {
        createsRepository.save(jpaCreatesMapper.createsToJpaCreates(creates));
    }

    @Override
    public void deleteById(Long uid, Long mid) {
        createsRepository.deleteById(CreatesKey.builder().uid(uid).mid(mid).build());
    }
}
