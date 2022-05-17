package io.alexdo.mixtech.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "playlists")
@Data // lombok plug-in; generate getter and setter
public class PlaylistEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    private String pname;
    private int privacy;
    private String description;

    public PlaylistEntity() {}

    public PlaylistEntity(String pname, int privacy, String description) {
        this.pname = pname;
        this.privacy = privacy;
        this.description = description;
    }
}
