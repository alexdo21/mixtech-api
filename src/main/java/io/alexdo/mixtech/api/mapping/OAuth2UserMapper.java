package io.alexdo.mixtech.api.mapping;

import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.application.domain.User;
import org.mapstruct.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class OAuth2UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "spotifyId", source = "id")
    @Mapping(target = "name", source = "displayName")
    public abstract User spotifyOAuth2UserToUser(SpotifyOAuth2User spotifyOAuth2User);

    @Mapping(target = "id", source = "spotifyId")
    @Mapping(target = "displayName", source = "name")
    @Mapping(target = "attributes", ignore = true)
    public abstract SpotifyOAuth2User userToSpotifyOAuth2User(User user);

    @AfterMapping
    public void mapAuthorities(@MappingTarget SpotifyOAuth2User spotifyOAuth2User) {
        spotifyOAuth2User.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
