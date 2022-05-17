package io.alexdo.mixtech.application.services.impl;

import io.alexdo.mixtech.jpa.dao.UserDao;
import io.alexdo.mixtech.jpa.entity.UserEntity;
import io.alexdo.mixtech.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public UserEntity getByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void create(UserEntity userEntity) {
        userDao.save(userEntity);
    }
}
