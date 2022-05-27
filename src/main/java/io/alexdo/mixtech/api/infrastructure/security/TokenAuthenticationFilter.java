package io.alexdo.mixtech.api.infrastructure.security;

import io.alexdo.mixtech.api.infrastructure.security.cryptography.TokenProvider;
import io.alexdo.mixtech.api.mapping.OAuth2UserMapper;
import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.application.logging.Logger;
import io.alexdo.mixtech.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;
    private final UserService userService;
    private final OAuth2UserMapper oAuth2UserMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(request);
            Logger.logInfo("Receiving token from request filter: " + jwt, this);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                String userId = tokenProvider.getUserIdFromToken(jwt);

                User user = userService.getBySpotifyId(userId);
                if (user != null) {
                    UserDetails userDetails = oAuth2UserMapper.userToSpotifyOAuth2User(user);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (UserDoesNotExistException e) {
            Logger.logError("User does not exist", this);
        } catch (Exception e) {
            Logger.logError("Could not set user authentication in security context", this);
        }
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ") ? bearerToken.substring(7) : null;
    }
}
