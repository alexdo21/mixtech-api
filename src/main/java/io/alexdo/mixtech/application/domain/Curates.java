package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Curates {
    private Long uid;
    private Long pid;
}
