package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.jpa.SongDao;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaMatchMapper {
    @Autowired
    private SongDao songDao;

    @Mapping(target = "song1", ignore = true)
    @Mapping(target = "song2", ignore = true)
    public abstract Match jpaMatchToMatch(MatchEntity matchEntity);

    @AfterMapping
    public void mapSongs(@MappingTarget Match.MatchBuilder matchBuilder, MatchEntity matchEntity) {
        if (Objects.nonNull(matchEntity.getSid1())) {
            matchBuilder.song1(songDao.findBySpotifyId(matchEntity.getSid1()).orElseThrow(() -> new ResourceNotFoundException("Song not found for: " + matchEntity.getSid1())));
        }
        if (Objects.nonNull(matchEntity.getSid2())) {
            matchBuilder.song2(songDao.findBySpotifyId(matchEntity.getSid2()).orElseThrow(() -> new ResourceNotFoundException("Song not found for: " + matchEntity.getSid2())));
        }
    }

    public abstract List<Match> jpaMatchesToMatches(List<MatchEntity> matchEntities);

    @Mapping(target = "sid1", source = "song1.spotifyId")
    @Mapping(target = "sid2", source = "song2.spotifyId")
    public abstract MatchEntity matchToJpaMatch(Match match);
}
