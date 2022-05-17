package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.jpa.entity.PlaylistEntity;
import io.alexdo.mixtech.api.dto.StandardResponse;
import io.alexdo.mixtech.application.services.CuratesService;
import io.alexdo.mixtech.application.services.FollowsService;
import io.alexdo.mixtech.application.services.PlaylistService;
import io.alexdo.mixtech.api.infrastructure.security.utils.SystemConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/playlist")
@RequiredArgsConstructor
public class PlaylistController {
    private final PlaylistService playlistService;
    private final CuratesService curatesService;
    private final FollowsService followsService;

    @ResponseBody
    @RequestMapping(value = "/all/{uid}", method = RequestMethod.GET)
    public List<PlaylistEntity> getAllPlaylists(@PathVariable Long uid) {
        return curatesService.getAllByUid(uid);
    }

    @ResponseBody
    @RequestMapping(value = "/display/{pid}", method = RequestMethod.GET)
    public PlaylistEntity getPlaylistByPid(@PathVariable Long pid) {
        return playlistService.getByPid(pid);
    }

    @ResponseBody
    @RequestMapping(value = "/all/following/{uid}", method = RequestMethod.GET)
    public List<PlaylistEntity> getAllFollowingPlaylists(@PathVariable Long uid) {
        return followsService.getAllByUid(uid);
    }

    @RequestMapping(value = "/create/{uid}", method = RequestMethod.POST)
    public void create(@PathVariable Long uid, @RequestBody PlaylistEntity playlistEntity) {
        playlistService.create(uid, playlistEntity);
    }

    @ResponseBody
    @RequestMapping(value = "/delete/{uid}/{pid}", method = RequestMethod.DELETE)
    public StandardResponse remove(@PathVariable Long uid, @PathVariable Long pid) {
        StandardResponse response = new StandardResponse();
        if ( !playlistService.remove(uid, pid) ) {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_UNAUTH_ACCESS);
        } else {
            response.setRet(SystemConstant.RET_SUC);
            response.setMsg(SystemConstant.MSG_SUCCESS);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/privacy/{uid}/{pid}", method = RequestMethod.PUT)
    public StandardResponse privacy(@PathVariable Long uid, @PathVariable Long pid, @RequestParam int privacy) {
        StandardResponse response = new StandardResponse();
        if ( !playlistService.privacy(uid, pid, privacy) ) {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_UNAUTH_ACCESS);
        } else {
            response.setRet(SystemConstant.RET_SUC);
            response.setMsg(SystemConstant.MSG_SUCCESS);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/follow/{uid}/{pid}", method = RequestMethod.POST)
    public StandardResponse follow(@PathVariable Long uid, @PathVariable Long pid) {
        StandardResponse response = new StandardResponse();
        if ( !followsService.follow(pid, uid)) {
            response.setRet(SystemConstant.RET_ERR);
            response.setMsg(SystemConstant.MSG_UNAUTH_ACCESS);
        } else {
            response.setRet(SystemConstant.RET_SUC);
            response.setMsg(SystemConstant.MSG_SUCCESS);
        }
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/unfollow/{uid}/{pid}", method = RequestMethod.DELETE)
    public StandardResponse unfollow(@PathVariable Long uid, @PathVariable Long pid) {
        StandardResponse response = new StandardResponse();
        followsService.unfollow(pid, uid);
        response.setRet(SystemConstant.RET_SUC);
        response.setMsg(SystemConstant.MSG_SUCCESS);
        return response;
    }
}
