package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Creates;
import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaCreatesMapper {
    public abstract CreatesEntity createsToJpaCreates(Creates creates);
    public abstract Creates jpaCreatesToCreates(CreatesEntity createsEntity);
    public abstract List<Creates> jpaCreatesListToCreatesList(List<CreatesEntity> createsEntityList);
}
