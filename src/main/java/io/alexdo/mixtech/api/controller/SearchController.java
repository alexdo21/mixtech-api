package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.api.dto.AdvanceSearchRequest;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import io.alexdo.mixtech.application.services.MatchService;
import io.alexdo.mixtech.application.services.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
@RequiredArgsConstructor
public class SearchController {
    private final SongService songService;
    private final MatchService matchService;

    @ResponseBody
    @RequestMapping(value = "/basic_page", method = RequestMethod.GET)
    public List<SongEntity> getAllSongsInPage(String name,
                                              @RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "size", defaultValue = "10") int size) {
        return songService.getAllByNameInPage(name, page, size);
    }

    @ResponseBody
    @RequestMapping(value = "/basic_matches", method = RequestMethod.GET)
    public List<DisplayMatchResponse> getAllMatchByName(@RequestParam String sname) {
        return matchService.displayMatchBySnmae(sname);
    }

    @ResponseBody
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public List<SongEntity> getAllByName(@RequestParam String sname) {
        return songService.getAllByName(sname);
    }

    @ResponseBody
    @RequestMapping(value = "/advance", method = RequestMethod.POST)
    public List<SongEntity> getAllByAudioFeatures(@RequestBody AdvanceSearchRequest request) {
        return songService.getAllByAudioFeatures(request);
    }
}
