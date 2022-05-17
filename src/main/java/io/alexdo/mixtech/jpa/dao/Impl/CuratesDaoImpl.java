package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.CuratesDao;
import io.alexdo.mixtech.jpa.entity.CuratesEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import io.alexdo.mixtech.jpa.repository.CuratesRepository;
import io.alexdo.mixtech.jpa.repository.custom.CuratesCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuratesDaoImpl implements CuratesDao {
    private final CuratesRepository curatesRepository;
    private final CuratesCustomRepository curatesCustomRepository;

    @Override
    public void save(CuratesEntity curatesEntity) {
        curatesRepository.save(curatesEntity);
    }

    @Override
    public void deleteById(CuratesKey curatesKey) {
        curatesRepository.deleteById(curatesKey);
    }

    @Override
    public CuratesEntity findOneByUidAndPid(Long uid, Long pid) {
        return curatesRepository.findOneByUidAndPid(uid, pid);
    }

    @Override
    public List<PlaylistEntity> findAllByUid(Long uid) {
        return curatesCustomRepository.findAllByUid(uid);
    }
}
