package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.User;
import io.alexdo.mixtech.jpa.entity.UserEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaUserMapper {
    public abstract User jpaUserToUser(UserEntity userEntity);
    public abstract UserEntity userToJpaUser(User user);
}
