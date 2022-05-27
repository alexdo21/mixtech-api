package io.alexdo.mixtech.api.infrastructure.security;

import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("isAuthenticated()")
public interface DefaultMethodSecurity {
}