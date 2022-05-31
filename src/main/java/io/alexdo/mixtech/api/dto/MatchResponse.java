package io.alexdo.mixtech.api.dto;

import io.alexdo.mixtech.application.domain.Match;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class MatchResponse extends RestResponse {
    private List<Match> matches;
}
