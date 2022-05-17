package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.jpa.entity.MatchEntity;
import io.alexdo.mixtech.jpa.entity.SongEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import io.alexdo.mixtech.api.dto.DisplayMatchResponse;
import io.alexdo.mixtech.api.dto.StandardResponse;
import io.alexdo.mixtech.application.services.CreatesService;
import io.alexdo.mixtech.application.services.MatchService;
import io.alexdo.mixtech.application.services.SongService;
import io.alexdo.mixtech.api.infrastructure.security.utils.SystemConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;
    private final CreatesService createsService;
    private final SongService songService;

    @ResponseBody
    @RequestMapping(value = "/getsongs/{mid}", method = RequestMethod.GET)
    public List<SongEntity> getAllByMid(@PathVariable Long mid) {
        MatchEntity matchEntity = matchService.getByMid(mid);
        List<SongEntity> songs = new ArrayList<>();
        songs.add(songService.getByID(matchEntity.getSpotifyUri1()));
        songs.add(songService.getByID(matchEntity.getSpotifyUri2()));
        return songs;
    }

    @ResponseBody
    @RequestMapping(value = "/all/{uid}", method = RequestMethod.GET)
    public List<DisplayMatchResponse> displayMatchByUid(@PathVariable Long uid) {
        return matchService.displayMatchByUid(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/incomplete/{uid}", method = RequestMethod.GET)
    public List<DisplayMatchResponse> displayIncompleteMatchByUid(@PathVariable Long uid) {
        return matchService.displayIncompleteMatchByUid(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/complete/{uid}", method = RequestMethod.GET)
    public List<DisplayMatchResponse> displayCompleteMatchByUid(@PathVariable Long uid) {
        return matchService.displayCompleteMatchByUid(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/create/{uid}", method = RequestMethod.POST)
    public void create(@RequestParam String spotifyUri1, @PathVariable Long uid) {
        matchService.create(spotifyUri1, uid);
    }

    @ResponseBody
    @RequestMapping(value = "/addsong/{uid}/{mid}", method = RequestMethod.POST)
    public StandardResponse addSong(@PathVariable Long uid, @PathVariable Long mid, @RequestParam String spotifyUri2) {
        StandardResponse response = new StandardResponse();
        int ret = matchService.addSong(spotifyUri2, mid, uid);
        if (ret == SystemConstant.RET_ERR_DUPSONG) {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_MATCH_DUPSONG);
        } else if (ret == SystemConstant.RET_ERR_DUPMATCH) {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_MATCH_DUPMATCH);
        } else if (ret == SystemConstant.RET_SUC){
            response.setRet(SystemConstant.RET_SUC);
            response.setMsg(SystemConstant.MSG_SUCCESS);
        } else {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_UNKNOWN_ERR);
        }
        return response;
    }

    @RequestMapping(value = "/delete/{mid}/{uid}", method = RequestMethod.DELETE)
    public void remove(@PathVariable Long mid, @PathVariable long uid) {
        CreatesKey key = new CreatesKey(uid, mid);
        createsService.remove(key);
        matchService.remove(mid);
    }
}
