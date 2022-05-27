package io.alexdo.mixtech.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "songs")
@Getter
@Setter
public class SongEntity {
    @Id
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
    private Long durationMs;
    private Integer timeSignature;
    private Integer popularity;
}
