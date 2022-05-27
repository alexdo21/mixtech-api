package io.alexdo.mixtech.api.dto;

import io.alexdo.mixtech.application.domain.User;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class UserResponse extends RestResponse {
    private User user;
}
