package io.alexdo.mixtech.api.infrastructure;

import io.alexdo.mixtech.api.infrastructure.security.DefaultMethodSecurity;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Objects;

public class SecuredRestController implements DefaultMethodSecurity {
    @Autowired
    private UserService userService;

    protected Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private SpotifyOAuth2User getCurrentSpotifyUser() {
        Authentication authentication = getAuthentication();
        return (SpotifyOAuth2User) authentication.getPrincipal();
    }

    private String getSpotifyId() {
        return getCurrentSpotifyUser().getId();
    }

    protected User getCurrentUser() throws UserDoesNotExistException {
        return userService.getBySpotifyId(getSpotifyId());
    }

    protected String getRequestUri() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getRequestURI();
    }
}
