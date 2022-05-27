package io.alexdo.mixtech.application.domain;

import lombok.Data;

@Data
public class MatchDisplay {
    private Long id;
    private String songName1;
    private String songName2;

    public MatchDisplay() {}

    public MatchDisplay(Long id, String songName1) {
        this.id = id;
        this.songName1 = songName1;
    }

    public MatchDisplay(Long id, String songName1, String songName2) {
        this.id = id;
        this.songName1 = songName1;
        this.songName2 = songName2;
    }
}
