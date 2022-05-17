package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.IncludesDao;
import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import io.alexdo.mixtech.jpa.repository.IncludesRepository;
import io.alexdo.mixtech.jpa.repository.custom.IncludesCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IncludesDaoImpl implements IncludesDao {
    private final IncludesRepository includesRepository;
    private final IncludesCustomRepository includesCustomRepository;

    @Override
    public void save(IncludesEntity includesEntity) {
        includesRepository.save(includesEntity);
    }

    @Override
    public void deleteById(IncludesKey includesKey) {
        includesRepository.deleteById(includesKey);
    }

    @Override
    public IncludesEntity findBySpotifyUriAndPid(String spotifyUri, Long pid) {
        return includesRepository.findBySpotifyUriAndPid(spotifyUri, pid);
    }

    public List<SongEntity> findAllByPid(Long pid) {
        return includesCustomRepository.findAllByPid(pid);
    }
}
