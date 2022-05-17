package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.ArtistKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(ArtistKey.class)
@Table(name = "artist")
@Data
public class ArtistEntity {
	@Id
	private String aname;
	@Id
	private String spotify_uri;
}
