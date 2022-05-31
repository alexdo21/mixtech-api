package io.alexdo.mixtech.spotify;

import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.application.domain.Song;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

public interface SpotifyPlaylistClient {
    void createPlaylistWithSongs(String spotifyId, Playlist playlist, List<Song> songs) throws IOException, ParseException, SpotifyWebApiException;
    void addNewSongsToExistingPlaylist(Playlist playlist, List<Song> songs) throws IOException, ParseException, SpotifyWebApiException;
}
