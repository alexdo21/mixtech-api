package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.api.dto.MatchDisplayResponse;
import io.alexdo.mixtech.api.dto.RestResponse;
import io.alexdo.mixtech.api.infrastructure.RestResponseConstant;
import io.alexdo.mixtech.api.infrastructure.SecuredRestController;
import io.alexdo.mixtech.application.domain.MatchDisplay;
import io.alexdo.mixtech.application.domain.exception.*;
import io.alexdo.mixtech.application.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/match")
@RequiredArgsConstructor
public class MatchController extends SecuredRestController {
    private final MatchService matchService;

    @RequestMapping(value = "/complete", method = RequestMethod.GET)
    public MatchDisplayResponse getCompleteMatches() {
        try {
            List<MatchDisplay> matchDisplays = matchService.getCompleteMatchesByUid(getCurrentUser().getId());
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .matchDisplays(matchDisplays)
                    .build();
        } catch (UserDoesNotExistException | ResourceNotFoundException e) {
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/incomplete", method = RequestMethod.GET)
    public MatchDisplayResponse getIncompleteMatches() {
        try {
            List<MatchDisplay> matchDisplays = matchService.getIncompleteMatchesByUid(getCurrentUser().getId());
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .matchDisplays(matchDisplays)
                    .build();
        } catch (UserDoesNotExistException | ResourceNotFoundException e) {
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @ResponseBody
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public MatchDisplayResponse getAllMatchesBySongName(@RequestParam String songName) {
        try {
            List<MatchDisplay> matchDisplays = matchService.getAllBySongName(songName);
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .matchDisplays(matchDisplays)
                    .build();
        } catch (ResourceNotFoundException e) {
            return MatchDisplayResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(MatchDisplay.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RestResponse createMatch(@RequestParam String songId1) {
        try {
            matchService.create(getCurrentUser().getId(), songId1);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (JpaUnableToSaveException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/pair/{mid}", method = RequestMethod.POST)
    public RestResponse pairMatch(@PathVariable Long mid, @RequestParam String songId2) {
        try {
            matchService.pair(getCurrentUser().getId(), mid, songId2);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (ResourceNotFoundException | MatchDuplicateSongException | MatchDuplicateMatchException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/delete/{mid}", method = RequestMethod.DELETE)
    public RestResponse deleteMatch(@PathVariable Long mid) {
        try {
            matchService.deleteByUidAndMid(getCurrentUser().getId(), mid);
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
