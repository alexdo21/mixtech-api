package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.CuratesDao;
import io.alexdo.mixtech.jpa.entity.CuratesEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import io.alexdo.mixtech.application.services.CuratesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuratesServiceImpl implements CuratesService {
    private final CuratesDao curatesDao;

    @Override
    public List<PlaylistEntity> getAllByUid(Long uid) {
        return curatesDao.findAllByUid(uid);
    }

    @Override
    public void create(Long uid, Long pid) {
        curatesDao.save(new CuratesEntity(uid, pid));
    }

    @Override
    public void remove(Long uid, Long pid) {
        curatesDao.deleteById(new CuratesKey(uid, pid));
    }
}
