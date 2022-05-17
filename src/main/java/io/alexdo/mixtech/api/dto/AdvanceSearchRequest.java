package io.alexdo.mixtech.api.dto;

import lombok.Data;

@Data
public class AdvanceSearchRequest {
    private Integer skey;
    private Integer smode;
    private Float danceability0;
    private Float danceability1;
    private Float energy0;
    private Float energy1;
    private Float loudness0;
    private Float loudness1;
    private Float speechiness0;
    private Float speechiness1;
    private Float acousticness0;
    private Float acousticness1;
    private Float instrumentalness0;
    private Float instrumentalness1;
    private Float liveness0;
    private Float liveness1;
    private Float valence0;
    private Float valence1;
    private Float tempo0;
    private Float tempo1;
}
