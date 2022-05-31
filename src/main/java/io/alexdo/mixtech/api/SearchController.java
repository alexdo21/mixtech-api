package io.alexdo.mixtech.api;

import io.alexdo.mixtech.api.dto.SongResponse;
import io.alexdo.mixtech.api.infrastructure.RestResponseConstant;
import io.alexdo.mixtech.api.infrastructure.SecuredRestController;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.application.domain.Song;
import io.alexdo.mixtech.application.domain.exception.ResourceNotFoundException;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.application.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class SearchController extends SecuredRestController {
    private final SongService songService;

    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public SongResponse getSongsByQuery(@RequestParam String query) {
        try {
            List<Song> songs = songService.getAllByQuery(query);
            return SongResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(Song.class, getRequestUri()))
                    .songs(songs)
                    .build();
        } catch (ResourceNotFoundException | SpotifyException e) {
            return SongResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(Song.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/basic/paged", method = RequestMethod.GET)
    public SongResponse getPagedSongsBySongName(@RequestParam String songName,
                                                @RequestParam(value = "page", defaultValue = "0") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            List<Song> songs = songService.getAllByNameInPage(songName, page, size);
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

    @RequestMapping(value = "/advanced", method = RequestMethod.POST)
    public SongResponse getSongsByAudioFeatures(@RequestBody AdvanceSearchRequest advanceSearchRequest) {
        try {
            List<Song> songs = songService.getAllByAudioFeatures(advanceSearchRequest);
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
}
