package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.application.domain.Includes;
import io.alexdo.mixtech.application.domain.Song;

import java.util.List;
import java.util.Optional;

public interface IncludesDao {
    Optional<Includes> findByPidAndSid(Long pid, String sid);
    Optional<List<Song>> findAllSongsByPid(Long Pid);
    void save(Includes includes);
    void deleteById(Long pid, String sid);
    void deleteByPid(Long pid);
}
