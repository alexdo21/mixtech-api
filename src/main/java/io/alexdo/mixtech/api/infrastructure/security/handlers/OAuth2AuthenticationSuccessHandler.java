package io.alexdo.mixtech.api.infrastructure.security.handlers;

import io.alexdo.mixtech.api.infrastructure.security.AppSecurityConfiguration;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.api.infrastructure.security.cryptography.TokenProvider;
import io.alexdo.mixtech.api.infrastructure.security.cryptography.HttpCookieOAuth2AuthorizationRequestRepository;
import io.alexdo.mixtech.api.mapping.OAuth2UserMapper;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import se.michaelthelin.spotify.SpotifyApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserService userService;
    private final OAuth2UserMapper oAuth2UserMapper;
    private final TokenProvider tokenProvider;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AppSecurityConfiguration appSecurityConfiguration;
    private final OAuth2AuthorizedClientRepository oAuth2AuthorizedClientRepository;
    private final SpotifyApi spotifyApi;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken auth = (OAuth2AuthenticationToken) authentication;
        OAuth2AuthorizedClient authorizedClient = oAuth2AuthorizedClientRepository.loadAuthorizedClient(auth.getAuthorizedClientRegistrationId(), authentication, request);
        spotifyApi.setAccessToken(authorizedClient.getAccessToken().getTokenValue());
        spotifyApi.setRefreshToken(Objects.requireNonNull(authorizedClient.getRefreshToken()).getTokenValue());
        SpotifyOAuth2User spotifyOAuth2User = (SpotifyOAuth2User) authentication.getPrincipal();
        userService.create(oAuth2UserMapper.spotifyOAuth2UserToUser(spotifyOAuth2User));
        String token = tokenProvider.createToken(authentication);
        Logger.logInfo("Token created: " + token, this);
        String targetUrl = UriComponentsBuilder.fromUriString(appSecurityConfiguration.getFrontEndRedirectUri())
                .queryParam("token", token)
                .build()
                .toUriString();
        clearAuthenticationAttributes(request);
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
        Logger.logInfo("Successful authentication, redirecting...", this);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
