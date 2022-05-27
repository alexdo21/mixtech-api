package io.alexdo.mixtech.api.dto;

import io.alexdo.mixtech.application.domain.MatchDisplay;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class MatchDisplayResponse extends RestResponse {
    private List<MatchDisplay> matchDisplays;
}
