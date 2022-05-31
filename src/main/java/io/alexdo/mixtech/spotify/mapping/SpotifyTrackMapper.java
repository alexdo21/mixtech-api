package io.alexdo.mixtech.spotify.mapping;

import com.google.common.collect.Streams;
import io.alexdo.mixtech.application.domain.Song;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Component;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForSeveralTracksRequest;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SpotifyTrackMapper {
    private final SpotifyApi spotifyApi;

    public Song spotifyTrackToSong(Track track) throws IOException, ParseException, SpotifyWebApiException {
        AudioFeatures audioFeatures = getAudioFeaturesForTrack(track);
        return Song.builder()
                .spotifyId(track.getId())
                .name(track.getName())
                .albumName(track.getAlbum().getName())
                .artistName(Arrays.stream(track.getArtists()).filter(Objects::nonNull).map(ArtistSimplified::getName).filter(Objects::nonNull).findFirst().orElse(null))
                .danceability(audioFeatures.getDanceability())
                .energy(audioFeatures.getEnergy())
                .key(audioFeatures.getKey())
                .loudness(audioFeatures.getLoudness())
                .mode(audioFeatures.getMode().getType())
                .speechiness(audioFeatures.getSpeechiness())
                .acousticness(audioFeatures.getAcousticness())
                .instrumentalness(audioFeatures.getInstrumentalness())
                .liveness(audioFeatures.getLiveness())
                .valence(audioFeatures.getValence())
                .tempo(audioFeatures.getTempo())
                .durationMs(audioFeatures.getDurationMs())
                .timeSignature(audioFeatures.getTimeSignature())
                .popularity(track.getPopularity())
                .build();
    }

    public List<Song> spotifyTracksToSongs(List<Track> tracks) throws IOException, ParseException, SpotifyWebApiException {
        List<AudioFeatures> audioFeaturesList = getAudioFeaturesForTracks(tracks);
        return Streams.zip(tracks.stream(), audioFeaturesList.stream(), (track, audioFeature) ->
                Song.builder()
                        .spotifyId(track.getId())
                        .name(track.getName())
                        .albumName(track.getAlbum().getName())
                        .artistName(Arrays.stream(track.getArtists()).filter(Objects::nonNull).map(ArtistSimplified::getName).filter(Objects::nonNull).findFirst().orElse(null))
                        .danceability(audioFeature.getDanceability())
                        .energy(audioFeature.getEnergy())
                        .key(audioFeature.getKey())
                        .loudness(audioFeature.getLoudness())
                        .mode(audioFeature.getMode().getType())
                        .speechiness(audioFeature.getSpeechiness())
                        .acousticness(audioFeature.getAcousticness())
                        .instrumentalness(audioFeature.getInstrumentalness())
                        .liveness(audioFeature.getLiveness())
                        .valence(audioFeature.getValence())
                        .tempo(audioFeature.getTempo())
                        .durationMs(audioFeature.getDurationMs())
                        .timeSignature(audioFeature.getTimeSignature())
                        .popularity(track.getPopularity())
                        .build()
                ).toList();
    }

    private AudioFeatures getAudioFeaturesForTrack(Track track) throws IOException, ParseException, SpotifyWebApiException {
        GetAudioFeaturesForTrackRequest getAudioFeaturesForTrackRequest = spotifyApi.getAudioFeaturesForTrack(track.getId()).build();
        return getAudioFeaturesForTrackRequest.execute();
    }

    private List<AudioFeatures> getAudioFeaturesForTracks(List<Track> tracks) throws IOException, ParseException, SpotifyWebApiException {
        GetAudioFeaturesForSeveralTracksRequest getAudioFeaturesForSeveralTracksRequest = spotifyApi.getAudioFeaturesForSeveralTracks(tracks.stream().map(Track::getId).toList().toArray(String[]::new)).build();
        return Arrays.asList(getAudioFeaturesForSeveralTracksRequest.execute());
    }
}
