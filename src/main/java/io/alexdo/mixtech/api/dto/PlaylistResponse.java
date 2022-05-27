package io.alexdo.mixtech.api.dto;

import io.alexdo.mixtech.application.domain.Playlist;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class PlaylistResponse extends RestResponse {
    private List<Playlist> playlists;
}
