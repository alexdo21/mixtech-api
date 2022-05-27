package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.api.dto.UserResponse;
import io.alexdo.mixtech.api.infrastructure.RestResponseConstant;
import io.alexdo.mixtech.api.infrastructure.SecuredRestController;
import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController extends SecuredRestController {
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
}
