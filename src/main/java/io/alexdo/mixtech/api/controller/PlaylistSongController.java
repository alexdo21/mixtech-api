package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.application.services.PlaylistSongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/playlist_song")
@RequiredArgsConstructor
public class PlaylistSongController {
    private final PlaylistSongService playlistSongService;

    @ResponseBody
    @RequestMapping(value = "/all/{pid}", method = RequestMethod.GET)
    public List<SongEntity> getAllByPid(@PathVariable Long pid) {
        return playlistSongService.getAllByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/add/{pid}", method = RequestMethod.PUT)
    public void add(@RequestParam String spotify_uri, @PathVariable Long pid) {
        playlistSongService.add(spotify_uri, pid);
    }

    @ResponseBody
    @RequestMapping(value = "/remove/{pid}", method = RequestMethod.DELETE)
    public void remove(@RequestParam String spotify_uri, @PathVariable Long pid) {
        playlistSongService.remove(spotify_uri, pid);
    }

}
