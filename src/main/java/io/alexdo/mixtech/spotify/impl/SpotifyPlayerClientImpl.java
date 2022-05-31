package io.alexdo.mixtech.spotify.impl;

import com.google.gson.JsonParser;
import io.alexdo.mixtech.spotify.SpotifyPlayerClient;
import lombok.RequiredArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlayingContext;
import se.michaelthelin.spotify.requests.data.player.GetInformationAboutUsersCurrentPlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.PauseUsersPlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.StartResumeUsersPlaybackRequest;
import se.michaelthelin.spotify.requests.data.player.TransferUsersPlaybackRequest;

import java.io.IOException;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SpotifyPlayerClientImpl implements SpotifyPlayerClient {
    private final SpotifyApi spotifyApi;

    @Override
    public void start(String spotifyId, String deviceId) throws IOException, ParseException, SpotifyWebApiException {
        var currentPlaybackState = getCurrentPlaybackState();
        if (Objects.isNull(currentPlaybackState) || !currentPlaybackState.getDevice().getId().equals(deviceId)) {
            transferPlayer(deviceId);
        }
        StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback().uris(JsonParser.parseString(String.format("[\"spotify:track:%s\"]", spotifyId)).getAsJsonArray()).position_ms(0).build();
        startResumeUsersPlaybackRequest.execute();
    }

    @Override
    public void resume(String deviceId) throws IOException, ParseException, SpotifyWebApiException {
        var currentPlaybackState = getCurrentPlaybackState();
        if (Objects.isNull(currentPlaybackState) || !currentPlaybackState.getDevice().getId().equals(deviceId)) {
            transferPlayer(deviceId);
        }
        StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi.startResumeUsersPlayback().build();
        startResumeUsersPlaybackRequest.execute();
    }

    @Override
    public void pause(String deviceId) throws IOException, ParseException, SpotifyWebApiException {
        PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback().device_id(deviceId).build();
        pauseUsersPlaybackRequest.execute();
    }

    private CurrentlyPlayingContext getCurrentPlaybackState() throws IOException, ParseException, SpotifyWebApiException {
        GetInformationAboutUsersCurrentPlaybackRequest getInformationAboutUsersCurrentPlaybackRequest = spotifyApi.getInformationAboutUsersCurrentPlayback().build();
        return getInformationAboutUsersCurrentPlaybackRequest.execute();
    }

    private void transferPlayer(String deviceId) throws IOException, ParseException, SpotifyWebApiException {
        TransferUsersPlaybackRequest transferUsersPlaybackRequest = spotifyApi.transferUsersPlayback(JsonParser.parseString(String.format("[\"%s\"]", deviceId)).getAsJsonArray()).play(false).build();
        transferUsersPlaybackRequest.execute();
    }
}
