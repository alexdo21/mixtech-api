package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Creates;
import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaCreatesMapper {
    public abstract CreatesEntity createsToJpaCreates(Creates creates);
}
