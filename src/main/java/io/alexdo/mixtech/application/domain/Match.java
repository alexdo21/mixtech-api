package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {
    private Long id;
    private Song song1;
    private Song song2;
}