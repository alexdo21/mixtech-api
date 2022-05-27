package io.alexdo.mixtech.jpa.dao.impl;

import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.jpa.dao.UserDao;
import io.alexdo.mixtech.jpa.mapping.JpaUserMapper;
import io.alexdo.mixtech.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final UserRepository userRepository;
    private final JpaUserMapper jpaUserMapper;

    @Override
    public void save(User user) {
        userRepository.save(jpaUserMapper.userToJpaUser(user));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(jpaUserMapper.jpaUserToUser(userRepository.findByEmail(email)));
    }

    @Override
    public Optional<User> findBySpotifyId(String spotifyId) {
        return Optional.ofNullable(jpaUserMapper.jpaUserToUser(userRepository.findBySpotifyId(spotifyId)));
    }
}
