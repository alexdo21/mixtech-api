package io.alexdo.mixtech.api.infrastructure.security.principal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class SpotifyOAuth2User implements OAuth2User, UserDetails {
    private String id;
    private String email;
    private String displayName;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public static SpotifyOAuth2User create(SpotifyOAuth2UserInfo spotifyOAuth2UserInfo, Map<String, Object> attributes) {
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new SpotifyOAuth2User(spotifyOAuth2UserInfo.getId(), spotifyOAuth2UserInfo.getEmail(), spotifyOAuth2UserInfo.getDisplayName(), authorities, attributes);
    }

    @Override
    public String getName() {
        return displayName;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
