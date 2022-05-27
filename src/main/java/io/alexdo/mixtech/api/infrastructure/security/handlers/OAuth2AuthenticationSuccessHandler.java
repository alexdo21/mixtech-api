package io.alexdo.mixtech.api.infrastructure.security.handlers;

import io.alexdo.mixtech.api.infrastructure.security.AppSecurityConfiguration;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.api.infrastructure.security.cryptography.TokenProvider;
import io.alexdo.mixtech.api.infrastructure.security.cryptography.HttpCookieOAuth2AuthorizationRequestRepository;
import io.alexdo.mixtech.api.mapping.OAuth2UserMapper;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final OAuth2UserMapper oAuth2UserMapper;
    private final TokenProvider tokenProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AppSecurityConfiguration appSecurityConfiguration;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SpotifyOAuth2User spotifyOAuth2User = (SpotifyOAuth2User) authentication.getPrincipal();
        userService.create(oAuth2UserMapper.spotifyOAuth2UserToUser(spotifyOAuth2User));
        String token = tokenProvider.createToken(authentication);
        Logger.logInfo("Token created: " + token, this);
        String targetUrl = UriComponentsBuilder.fromUriString(appSecurityConfiguration.getFrontendRedirectUri())
                .queryParam("token", token)
                .build()
                .toUriString();
        clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
        Logger.logInfo("Successful authentication, redirecting...", this);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
