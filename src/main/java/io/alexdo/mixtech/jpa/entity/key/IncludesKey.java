package io.alexdo.mixtech.jpa.entity.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class IncludesKey implements Serializable {
    private String spotifyUri;
    private Long pid;

    public IncludesKey() {}

    public IncludesKey(String spotify_uri, Long pid) {
        this.spotifyUri = spotify_uri;
        this.pid = pid;
    }
}
