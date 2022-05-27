package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Includes {
    private Long pid;
    private String sid;
}
