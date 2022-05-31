package io.alexdo.mixtech.jpa.impl;

import io.alexdo.mixtech.application.domain.Includes;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.jpa.IncludesDao;
import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import io.alexdo.mixtech.jpa.mapping.JpaIncludesMapper;
import io.alexdo.mixtech.jpa.mapping.JpaSongMapper;
import io.alexdo.mixtech.jpa.repository.IncludesRepository;
import io.alexdo.mixtech.jpa.repository.custom.IncludesCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IncludesDaoImpl implements IncludesDao {
    private final IncludesRepository includesRepository;
    private final IncludesCustomRepository includesCustomRepository;
    private final JpaIncludesMapper jpaIncludesMapper;
    private final JpaSongMapper jpaSongMapper;

    @Override
    public Optional<Includes> findByPidAndSid(Long pid, String sid) {
        return Optional.ofNullable(jpaIncludesMapper.jpaIncludesToIncludes(includesRepository.findByPidAndSid(pid, sid)));
    }

    public Optional<List<Song>> findAllSongsByPid(Long pid) {
        return Optional.ofNullable(jpaSongMapper.jpaSongsToSongs(includesCustomRepository.findAllSongsByPid(pid)));
    }

    @Override
    public void save(Includes includes) {
        includesRepository.save(jpaIncludesMapper.includesToJpaIncludes(includes));
    }

    @Override
    public void deleteById(Long pid, String sid) {
        includesRepository.deleteById(IncludesKey.builder().pid(pid).sid(sid).build());
    }

    @Override
    public void deleteByPid(Long pid) {
        includesRepository.deleteByPid(pid);
    }
}
