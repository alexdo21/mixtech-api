package io.alexdo.mixtech.api.infrastructure.security.cryptography;

import io.alexdo.mixtech.api.infrastructure.security.AppSecurityConfiguration;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2User;
import io.alexdo.mixtech.application.logging.Logger;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenProvider {
    private final AppSecurityConfiguration appSecurityConfiguration;

    public String createToken(Authentication authentication) {
        SpotifyOAuth2User spotifyOAuth2User = (SpotifyOAuth2User) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + Long.parseLong(appSecurityConfiguration.getTokenExpirationMSec()));
        return Jwts.builder()
                .setSubject(spotifyOAuth2User.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, appSecurityConfiguration.getTokenSecret())
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appSecurityConfiguration.getTokenSecret())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appSecurityConfiguration.getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            Logger.logError("Invalid JWT signature", this);
        } catch (MalformedJwtException e) {
            Logger.logError("Invalid JWT token", this);
        } catch (ExpiredJwtException e) {
            Logger.logError("Expired JWT token", this);
        } catch (UnsupportedJwtException e) {
            Logger.logError("Unsupported JWT token", this);
        } catch (IllegalArgumentException e) {
            Logger.logError("JWT claims string is empty", this);
        }
        return false;
    }
}
