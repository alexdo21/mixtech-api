package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Match {
    private Long id;
    private String sid1;
    private String sid2;
}
