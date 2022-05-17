package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.IncludesDao;
import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import io.alexdo.mixtech.application.services.PlaylistSongService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaylistSongServiceImpl implements PlaylistSongService {
    private final IncludesDao includesDao;

    @Override
    public List<SongEntity> getAllByPid(Long pid) {
        return includesDao.findAllByPid(pid);
    }

    @Override
    public void add(String spotifyUri, Long pid) {
        if (includesDao.findBySpotifyUriAndPid(spotifyUri, pid) != null) {
//            // If already exist...
        }
        includesDao.save(new IncludesEntity(spotifyUri, pid));
    }

    @Override
    public void remove(String spotifyUri, Long pid) {
        includesDao.deleteById(new IncludesKey(spotifyUri, pid));
    }
}
