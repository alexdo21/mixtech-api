package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Playlist {
    private Long id;
    private String name;
    private String description;
    private String spotifyId;
}