package io.alexdo.mixtech.api;

import io.alexdo.mixtech.api.dto.AccessTokenResponse;
import io.alexdo.mixtech.api.dto.RestResponse;
import io.alexdo.mixtech.api.dto.UserResponse;
import io.alexdo.mixtech.api.infrastructure.RestResponseConstant;
import io.alexdo.mixtech.api.infrastructure.SecuredRestController;
import io.alexdo.mixtech.application.UserService;
import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.SpotifyException;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.application.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController extends SecuredRestController {
    private final UserService userService;
    private final SongService songService;

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public UserResponse getUserInfo() {
        try {
            User user = getCurrentUser();
            return UserResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(User.class, getRequestUri()))
                    .user(user)
                    .build();
        } catch (UserDoesNotExistException e) {
            return UserResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(User.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/access-token", method = RequestMethod.GET)
    public AccessTokenResponse getUserAccessToken() {
        String accessToken = userService.getUserAccessToken();
        return AccessTokenResponse.builder()
                .status(RestResponseConstant.SUCCESS)
                .description(RestResponseConstant.DESCRIPTION(String.class, getRequestUri()))
                .accessToken(accessToken)
                .build();
    }

    @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
    public AccessTokenResponse getRefreshToken() {
        try {
            String refreshToken = userService.getRefreshToken(getAuthentication());
            return AccessTokenResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .description(RestResponseConstant.DESCRIPTION(String.class, getRequestUri()))
                    .accessToken(refreshToken)
                    .build();
        } catch (SpotifyException e) {
            return AccessTokenResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .description(RestResponseConstant.DESCRIPTION(String.class, getRequestUri()))
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/player/start/{songId}/{deviceId}", method = RequestMethod.POST)
    public RestResponse startSong(@PathVariable String songId, @PathVariable String deviceId) {
        try {
            songService.start(songId, deviceId);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (SpotifyException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/player/resume/{deviceId}", method = RequestMethod.POST)
    public RestResponse resumeSong(@PathVariable String deviceId) {
        try {
            songService.resume(deviceId);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (SpotifyException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }

    @RequestMapping(value = "/player/pause/{deviceId}", method = RequestMethod.POST)
    public RestResponse pauseSong(@PathVariable String deviceId) {
        try {
            songService.pause(deviceId);
            return RestResponse.builder()
                    .status(RestResponseConstant.SUCCESS)
                    .build();
        } catch (SpotifyException e) {
            return RestResponse.builder()
                    .status(RestResponseConstant.FAILURE)
                    .errorMessage(RestResponseConstant.ERROR(e.getClass(), e.getMessage()))
                    .build();
        }
    }
}
