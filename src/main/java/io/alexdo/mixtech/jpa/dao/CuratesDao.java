package io.alexdo.mixtech.jpa.dao;


import io.alexdo.mixtech.jpa.entity.CuratesEntity;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.jpa.entity.key.CuratesKey;

import java.util.List;

public interface CuratesDao {
    void save(CuratesEntity curatesEntity);
    void deleteById(CuratesKey curatesKey);
    CuratesEntity findOneByUidAndPid(Long uid, Long pid);
    List<PlaylistEntity> findAllByUid(Long uid);
}