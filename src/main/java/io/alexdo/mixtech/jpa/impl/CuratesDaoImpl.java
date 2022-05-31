package io.alexdo.mixtech.jpa.impl;

import io.alexdo.mixtech.application.domain.Curates;
import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.jpa.CuratesDao;
import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import io.alexdo.mixtech.jpa.mapping.JpaCuratesMapper;
import io.alexdo.mixtech.jpa.mapping.JpaPlaylistMapper;
import io.alexdo.mixtech.jpa.repository.CuratesRepository;
import io.alexdo.mixtech.jpa.repository.custom.CuratesCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuratesDaoImpl implements CuratesDao {
    private final CuratesRepository curatesRepository;
    private final CuratesCustomRepository curatesCustomRepository;
    private final JpaCuratesMapper jpaCuratesMapper;
    private final JpaPlaylistMapper jpaPlaylistMapper;

    @Override
    public void save(Curates curates) {
        curatesRepository.save(jpaCuratesMapper.curatesToJpaCurates(curates));
    }

    @Override
    public void deleteById(Long uid, Long pid) {
        curatesRepository.deleteById(CuratesKey.builder().uid(uid).pid(pid).build());
    }

    @Override
    public Optional<List<Playlist>> findAllByUid(Long uid) {
        return Optional.ofNullable(jpaPlaylistMapper.jpaPlaylistsToPlaylists(curatesCustomRepository.findAllByUid(uid)));
    }
}
