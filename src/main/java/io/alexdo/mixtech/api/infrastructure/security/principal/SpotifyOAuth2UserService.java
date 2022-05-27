package io.alexdo.mixtech.api.infrastructure.security.principal;

import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2UserInfo;
import io.alexdo.mixtech.application.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.SpotifyApi;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SpotifyOAuth2UserService extends DefaultOAuth2UserService {
    private final SpotifyApi spotifyApi;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        Logger.logInfo("Getting access token", this);
        spotifyApi.setAccessToken(oAuth2UserRequest.getAccessToken().getTokenValue());
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        Map<String, Object> oAuth2UserAttributes = oAuth2User.getAttributes();
        SpotifyOAuth2UserInfo spotifyOAuth2UserInfo = new SpotifyOAuth2UserInfo(oAuth2UserAttributes);
        Logger.logInfo("User principal created", this);
        return SpotifyOAuth2User.create(spotifyOAuth2UserInfo, oAuth2UserAttributes);
    }
}
