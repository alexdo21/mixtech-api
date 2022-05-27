package io.alexdo.mixtech.api.infrastructure.security.handlers;

import io.alexdo.mixtech.api.infrastructure.security.AppSecurityConfiguration;
import io.alexdo.mixtech.api.infrastructure.security.cryptography.HttpCookieOAuth2AuthorizationRequestRepository;
import io.alexdo.mixtech.application.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final AppSecurityConfiguration appSecurityConfiguration;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        String targetUrl = UriComponentsBuilder.fromUriString(appSecurityConfiguration.getFrontendRedirectUri())
                .queryParam("error", exception.getLocalizedMessage())
                .build().toUriString();
        httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
        Logger.logInfo("FAILURE", this);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
