package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private Long id;
    private String spotifyId;
    private String email;
    private String name;
}
