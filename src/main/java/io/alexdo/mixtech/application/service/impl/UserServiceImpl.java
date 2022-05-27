package io.alexdo.mixtech.application.service.impl;

import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.application.domain.exception.UserDoesNotExistException;
import io.alexdo.mixtech.jpa.dao.UserDao;
import io.alexdo.mixtech.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public void create(User user) {
        if (userDao.findByEmail(user.getEmail()).isEmpty()) {
            userDao.save(user);
        }
    }

    @Override
    public User getBySpotifyId(String spotifyId) throws UserDoesNotExistException {
        return userDao.findBySpotifyId(spotifyId).orElseThrow(() -> new UserDoesNotExistException("User does not exist"));
    }
}
