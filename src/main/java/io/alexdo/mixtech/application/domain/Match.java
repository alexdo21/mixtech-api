package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {
    private Long mid;
    private String spotifyUri1;
    private String spotifyUri2;
}
