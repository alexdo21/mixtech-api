package io.alexdo.mixtech.application.service.impl;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.jpa.dao.SongDao;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {
    private final SongDao songDao;

    @Override
    public List<Song> getAllByName(String name) {
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        return songDao.findByNameLike("%" + name + "%",sort).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name: " + name));
    }

    @Override
    public List<Song> getAllByNameInPage(String name, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "popularity");
        Pageable pageable = PageRequest.of(page, size, sort);
        return songDao.findByNameLike("%" + name + "%", pageable).orElseThrow(() -> new ResourceNotFoundException("Song not found for song name like: " + name));
    }

    @Override
    public List<Song> getAllByAudioFeatures(AdvanceSearchRequest advanceSearchRequest) {
        return songDao.findAllByAudioFeatures(advanceSearchRequest).orElseThrow(() -> new ResourceNotFoundException("Song not found for audio feature query of: " + advanceSearchRequest));
    }
}
