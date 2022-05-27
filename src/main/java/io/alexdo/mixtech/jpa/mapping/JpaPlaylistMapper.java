package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaPlaylistMapper {
    public abstract Playlist jpaPlaylistToPlaylist(PlaylistEntity playlistEntity);
    public abstract PlaylistEntity playlistToJpaPlaylist(Playlist playlist);
    public abstract List<Playlist> jpaPlaylistsToPlaylists(List<PlaylistEntity> playlistEntities);
}
