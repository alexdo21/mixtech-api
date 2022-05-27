package io.alexdo.mixtech.api.dto;

import io.alexdo.mixtech.application.domain.Song;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class SongResponse extends RestResponse {
    private List<Song> songs;
}