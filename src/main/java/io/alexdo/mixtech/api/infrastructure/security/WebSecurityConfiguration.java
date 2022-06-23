package io.alexdo.mixtech.api.infrastructure.security;

import io.alexdo.mixtech.api.infrastructure.security.cryptography.HttpCookieOAuth2AuthorizationRequestRepository;
import io.alexdo.mixtech.api.infrastructure.security.handlers.OAuth2AuthenticationFailureHandler;
import io.alexdo.mixtech.api.infrastructure.security.handlers.OAuth2AuthenticationSuccessHandler;
import io.alexdo.mixtech.api.infrastructure.security.principal.SpotifyOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final SpotifyOAuth2UserService spotifyOAuth2UserService;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
    private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
    private final TokenAuthenticationFilter tokenAuthenticationFilter;
    private final String[] REACT_WHITE_LIST = {
            "/",
            "/error",
            "/favicon.ico",
            "/**/*.png",
            "/**/*.gif",
            "/**/*.svg",
            "/**/*.jpg",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"
    };

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .authorizeRequests(config -> {
                    config.antMatchers(REACT_WHITE_LIST).permitAll();
                    config.antMatchers("/oauth2/**").permitAll();
                    config.anyRequest().authenticated();
                })
                .oauth2Login(config -> {
                    config.authorizationEndpoint(subConfig -> {
                        subConfig.baseUri("/oauth2/authorization");
                        subConfig.authorizationRequestRepository(httpCookieOAuth2AuthorizationRequestRepository);
                    });
                    config.redirectionEndpoint(subConfig -> subConfig.baseUri("/oauth2/callback/*"));
                    config.userInfoEndpoint(subConfig -> subConfig.userService(spotifyOAuth2UserService));
                    config.successHandler(oAuth2AuthenticationSuccessHandler);
                    config.failureHandler(oAuth2AuthenticationFailureHandler);
                })
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}