package io.alexdo.mixtech.spotify.impl;

import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.spotify.SpotifyPlaylistClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.IPlaylistItem;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpotifyPlaylistClientImpl implements SpotifyPlaylistClient {
    private final SpotifyApi spotifyApi;

    @Override
    public void createPlaylistWithSongs(String spotifyId, Playlist playlist, List<Song> songs) throws IOException, ParseException, SpotifyWebApiException {
        var spotifyPlaylist = createPlaylist(spotifyId, playlist);
        addSongsToPlaylist(spotifyPlaylist.getId(), songs);
    }

    @Override
    public void addNewSongsToExistingPlaylist(Playlist playlist, List<Song> songs) throws IOException, ParseException, SpotifyWebApiException {
        GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlist.getSpotifyId()).limit(50).build();
        var spotifyPlaylistTracks = getPlaylistsItemsRequest.execute();
        List<String> existingTrackIds = Arrays.stream(spotifyPlaylistTracks.getItems()).map(PlaylistTrack::getTrack).map(IPlaylistItem::getId).toList();
        List<Song> newSongs = songs.stream().filter(song -> !existingTrackIds.contains(song.getSpotifyId())).toList();
        addSongsToPlaylist(playlist.getSpotifyId(), newSongs);
    }

    private se.michaelthelin.spotify.model_objects.specification.Playlist createPlaylist(String spotifyId, Playlist playlist) throws IOException, ParseException, SpotifyWebApiException {
        CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(spotifyId, playlist.getName())
                .collaborative(false)
                .public_(false)
                .description(playlist.getDescription())
                .build();
        return createPlaylistRequest.execute();
    }

    private void addSongsToPlaylist(String playlistId, List<Song> songs) throws IOException, ParseException, SpotifyWebApiException {
        AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(playlistId,
                songs.stream()
                        .map(Song::getSpotifyId)
                        .map("spotify:track:"::concat)
                        .toArray(String[]::new))
                .build();
        addItemsToPlaylistRequest.execute();
    }
}
