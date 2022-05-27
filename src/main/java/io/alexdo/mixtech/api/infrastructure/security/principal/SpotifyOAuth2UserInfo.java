package io.alexdo.mixtech.api.infrastructure.security.principal;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class SpotifyOAuth2UserInfo {
    private Map<String, Object> attributes;

    public String getId() { return (String) attributes.get("id"); }
    public String getEmail() { return (String) attributes.get("email"); }
    public String getDisplayName() { return (String) attributes.get("display_name"); }
}
