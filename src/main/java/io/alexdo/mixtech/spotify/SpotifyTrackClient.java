package io.alexdo.mixtech.spotify;

import io.alexdo.mixtech.application.domain.Song;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

import java.io.IOException;

public interface SpotifyTrackClient {
    Song getTrack(String spotifyId) throws IOException, ParseException, SpotifyWebApiException;
}
