package io.alexdo.mixtech.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class RestResponse {
    private String status;
    private String description;
    private String errorMessage;
}
