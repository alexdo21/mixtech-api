package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaSongMapper {
    public abstract Song jpaSongToSong(SongEntity songEntity);
    public abstract List<Song> jpaSongsToSongs(List<SongEntity> songEntities);
    public abstract SongEntity songToJpaSong(Song song);
}
