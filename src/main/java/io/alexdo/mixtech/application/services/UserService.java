package io.alexdo.mixtech.application.services;

import io.alexdo.mixtech.jpa.entity.UserEntity;

public interface UserService {
    UserEntity getByEmail(String email);
    void create(UserEntity userEntity);
}
