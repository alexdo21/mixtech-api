package io.alexdo.mixtech.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class AccessTokenResponse extends RestResponse {
    private String accessToken;
}
