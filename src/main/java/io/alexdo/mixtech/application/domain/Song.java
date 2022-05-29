package io.alexdo.mixtech.application.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Song {
    private String spotifyId;
    private String name;
    private String albumName;
    private String artistName;
    private Float danceability;
    private Float energy;
    private Integer key;
    private Float loudness;
    private Integer mode;
    private Float speechiness;
    private Float acousticness;
    private Float instrumentalness;
    private Float liveness;
    private Float valence;
    private Float tempo;
    private Integer durationMs;
    private Integer timeSignature;
    private Integer popularity;
}
