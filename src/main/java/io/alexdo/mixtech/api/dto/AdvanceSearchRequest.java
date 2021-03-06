package io.alexdo.mixtech.api.dto;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class AdvanceSearchRequest {
    private Integer key;
    private Integer mode;
    private Integer timeSignature;
    private Long durationMs0;
    private Long durationMs1;
    private Float tempo0;
    private Float tempo1;
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

    @Override
    public String toString() {
        List<String> fields = Arrays.asList(key.toString(), mode.toString(), timeSignature.toString(),
                durationMs0.toString(), getDurationMs1().toString(),
                tempo0.toString(), tempo1.toString(),
                danceability0.toString(), danceability1.toString(),
                energy0.toString(), energy1.toString(),
                loudness0.toString(), loudness1.toString(),
                speechiness0.toString(), speechiness1.toString(),
                acousticness0.toString(), acousticness1.toString(),
                instrumentalness0.toString(), instrumentalness1.toString(),
                liveness0.toString(), liveness1.toString(),
                valence0.toString(), valence1.toString());
        return String.join(", ", fields);
    }
}
