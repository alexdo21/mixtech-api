package io.alexdo.mixtech.jpa.dao.Impl;

import io.alexdo.mixtech.jpa.dao.SongDao;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.repository.SongRepository;
import io.alexdo.mixtech.jpa.repository.custom.SongCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongDaoImpl implements SongDao {
    private final SongRepository songRepository;
    private final SongCustomRepository songCustomRepository;

    @Override
    public List<SongEntity> findBySnameLike(String sname, Sort sort) {
        return songRepository.findBySnameLike(sname, sort);
    }

    @Override
    public SongEntity findBySpotifyID(String spotfiyID) {
        return songRepository.findBySpotifyID(spotfiyID);
    }

    @Override
    public List<SongEntity> findBySnameLike(String sname, Pageable pageable) {
        return songRepository.findBySnameLike(sname, pageable);
    }

    @Override
    public List<SongEntity> findAllByAudioFeatures(String query) {
        return songCustomRepository.findAllByAudioFeatures(query);
    }
}
