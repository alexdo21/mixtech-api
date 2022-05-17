package io.alexdo.mixtech.jpa.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "matches")
@Data
public class MatchEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mid;

    //private String mname;
    @Column(name = "spotify_uri1")
    private String spotifyUri1;
    @Column(name = "spotify_uri2")
    private String spotifyUri2;

    public MatchEntity() {}

    public MatchEntity(String spotifyUri1) {
        this.spotifyUri1 = spotifyUri1;
    }

    public MatchEntity(String mname, String spotifyUri1) {
        //this.mname = mname;
        this.spotifyUri1 = spotifyUri1;
    }

    public MatchEntity(String mname, String spotify_uri1, String spotify_uri2) {
        //this.mname = mname;
        this.spotifyUri1 = spotify_uri1;
        this.spotifyUri2 = spotify_uri2;
    }
}
