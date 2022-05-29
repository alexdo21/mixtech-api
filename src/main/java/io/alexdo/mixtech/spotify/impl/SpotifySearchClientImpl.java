package io.alexdo.mixtech.spotify.impl;

import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.spotify.SpotifySearchClient;
import io.alexdo.mixtech.spotify.mapping.SpotifyTrackMapper;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotifySearchClientImpl implements SpotifySearchClient {
    private final SpotifyApi spotifyApi;
    private final SpotifyTrackMapper spotifyTrackMapper;

    @Override
    public List<Song> searchTracks(String query) throws IOException, ParseException, SpotifyWebApiException {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query).limit(10).build();
        Paging<Track> tracks = searchTracksRequest.execute();
        return spotifyTrackMapper.spotifyTracksToSongs(Arrays.asList(tracks.getItems()));
    }
}