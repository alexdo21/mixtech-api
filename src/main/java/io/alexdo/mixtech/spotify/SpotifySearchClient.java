package io.alexdo.mixtech.spotify;

import io.alexdo.mixtech.application.domain.Song;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;
import java.util.List;

public interface SpotifySearchClient {
    List<Song> searchTracks(String query) throws IOException, ParseException, SpotifyWebApiException;
}
