package io.alexdo.mixtech.spotify.impl;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.spotify.SpotifyTrackClient;
import io.alexdo.mixtech.spotify.mapping.SpotifyTrackMapper;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.requests.data.tracks.GetTrackRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SpotifyTrackClientImpl implements SpotifyTrackClient {
    private final SpotifyApi spotifyApi;
    private final SpotifyTrackMapper spotifyTrackMapper;

    @Override
    public Song getTrack(String spotifyId) throws IOException, ParseException, SpotifyWebApiException {
        GetTrackRequest getTrackRequest = spotifyApi.getTrack(spotifyId).build();
        return spotifyTrackMapper.spotifyTrackToSong(getTrackRequest.execute());
    }
}
