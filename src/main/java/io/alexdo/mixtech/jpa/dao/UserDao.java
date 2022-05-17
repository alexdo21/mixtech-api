package io.alexdo.mixtech.jpa.dao;

import io.alexdo.mixtech.jpa.entity.UserEntity;

public interface UserDao {
    void save(UserEntity userEntity);
    UserEntity findByEmail(String email);
}
