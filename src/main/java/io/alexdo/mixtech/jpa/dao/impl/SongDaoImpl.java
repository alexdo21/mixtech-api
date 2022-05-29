package io.alexdo.mixtech.jpa.dao.impl;

import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.jpa.dao.SongDao;
import io.alexdo.mixtech.jpa.mapping.JpaSongMapper;
import io.alexdo.mixtech.jpa.repository.SongRepository;
import io.alexdo.mixtech.jpa.repository.custom.SongCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongDaoImpl implements SongDao {
    private final SongRepository songRepository;
    private final SongCustomRepository songCustomRepository;
    private final JpaSongMapper jpaSongMapper;

    @Override
    public Optional<List<Song>> findByNameLike(String name) {
        return Optional.ofNullable(jpaSongMapper.jpaSongsToSongs(songRepository.findByNameLike("%" + name + "%", Sort.by(Sort.Direction.DESC, "popularity"))));
    }

    @Override
    public Optional<List<Song>> findByNameLike(String name, Pageable pageable) {
        return Optional.ofNullable(jpaSongMapper.jpaSongsToSongs(songRepository.findByNameLike(name, pageable)));
    }

    @Override
    public Optional<List<Song>> findAllByAudioFeatures(AdvanceSearchRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select * from songs s where");
        stringBuilder.append(" s.key = ").append(request.getKey().toString());
        stringBuilder.append(" AND s.mode = ").append(request.getMode().toString());
        if (request.getDanceability0() != 0) {
            stringBuilder.append(" AND s.danceability >= ").append(request.getDanceability0().toString());
        }
        if (request.getDanceability1() != 1) {
            stringBuilder.append(" AND s.danceability <= ").append(request.getDanceability1().toString());
        }
        if (request.getEnergy0() != 0) {
            stringBuilder.append(" AND s.energy >= ").append(request.getEnergy0().toString());
        }
        if (request.getEnergy1() != 1) {
            stringBuilder.append(" AND s.energy <= ").append(request.getEnergy1().toString());
        }
        if (request.getLoudness0() != -60) {
            stringBuilder.append(" AND s.loudness >= ").append(request.getLoudness0().toString());
        }
        if (request.getLoudness1() != 0) {
            stringBuilder.append(" AND s.loudness <= ").append(request.getLoudness1().toString());
        }
        if (request.getSpeechiness0() != 0) {
            stringBuilder.append(" AND s.speechiness >= ").append(request.getSpeechiness0().toString());
        }
        if (request.getSpeechiness1() != 1) {
            stringBuilder.append(" AND s.speechiness <= ").append(request.getSpeechiness1().toString());
        }
        if (request.getAcousticness0() != 0) {
            stringBuilder.append(" AND s.acousticness >= ").append(request.getAcousticness0().toString());
        }
        if (request.getAcousticness1() != 1) {
            stringBuilder.append(" AND s.acousticness <= ").append(request.getAcousticness1().toString());
        }
        if (request.getInstrumentalness0() != 0) {
            stringBuilder.append(" AND s.instrumentalness >= ").append(request.getInstrumentalness0().toString());
        }
        if (request.getInstrumentalness1() != 1) {
            stringBuilder.append(" AND s.instrumentalness <= ").append(request.getInstrumentalness1().toString());
        }
        if (request.getLiveness0() != 0) {
            stringBuilder.append(" AND s.liveness >= ").append(request.getLiveness0().toString());
        }
        if (request.getLiveness1() != 1) {
            stringBuilder.append(" AND s.liveness <= ").append(request.getLiveness1().toString());
        }
        if (request.getValence0() != 0) {
            stringBuilder.append(" AND s.valence >= ").append(request.getValence0().toString());
        }
        if (request.getValence1() != 1) {
            stringBuilder.append(" AND s.valence <= ").append(request.getValence1().toString());
        }
        if (request.getTempo0() != 0) {
            stringBuilder.append(" AND s.tempo >= ").append(request.getTempo0().toString());
        }
        if (request.getTempo1() != 250) {
            stringBuilder.append(" AND s.tempo <= ").append(request.getTempo1().toString());
        }
        stringBuilder.append(" order by popularity desc;");
        return Optional.ofNullable(jpaSongMapper.jpaSongsToSongs(songCustomRepository.findAllByAudioFeatures(stringBuilder.toString())));
    }
}
