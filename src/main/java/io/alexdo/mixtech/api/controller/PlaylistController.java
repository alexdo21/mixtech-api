package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.api.dto.PlaylistResponse;
import io.alexdo.mixtech.api.dto.SongResponse;
import io.alexdo.mixtech.api.dto.RestResponse;
import io.alexdo.mixtech.api.infrastructure.RestResponseConstant;
import io.alexdo.mixtech.api.infrastructure.SecuredRestController;
import io.alexdo.mixtech.application.domain.Playlist;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.PlaylistDuplicateSongException;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.application.service.PlaylistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/playlist")
@RequiredArgsConstructor
public class PlaylistController extends SecuredRestController {
    private final PlaylistService playlistService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public PlaylistResponse getAllPlaylists() {
        try {
            List<Playlist> playlists = playlistService.getAllByUid(getCurrentUser().getId());
            return PlaylistResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(Playlist.class, getRequestUri()))
                    .playlists(playlists)
                    .build();
        } catch (UserDoesNotExistException | ResourceNotFoundException e) {
            return PlaylistResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(Playlist.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/songs/{pid}", method = RequestMethod.GET)
    public SongResponse getAllSongsInPlaylist(@PathVariable Long pid) {
        try {
            List<Song> songs = playlistService.getAllSongsByPid(pid);
            return SongResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(Song.class, getRequestUri()))
                    .songs(songs)
                    .build();
        } catch (ResourceNotFoundException e) {
            return SongResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(Song.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RestResponse createPlaylist(@RequestBody Playlist playlist) {
        try {
            playlistService.create(getCurrentUser().getId(), playlist);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (UserDoesNotExistException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/songs/add/{pid}", method = RequestMethod.POST)
    public RestResponse addSongToPlaylist(@PathVariable Long pid, @RequestParam String songId) {
        try {
            playlistService.addSong(pid, songId);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (PlaylistDuplicateSongException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/songs/delete/{pid}", method = RequestMethod.DELETE)
    public RestResponse deleteSongFromPlaylist(@PathVariable Long pid, @RequestParam String songId) {
        try {
            playlistService.deleteSong(pid, songId);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (PlaylistDuplicateSongException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/delete/{pid}", method = RequestMethod.DELETE)
    public RestResponse deletePlaylist(@PathVariable Long pid) {
        try {
            playlistService.deleteByUidAndPid(getCurrentUser().getId(), pid);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (UserDoesNotExistException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }
}
