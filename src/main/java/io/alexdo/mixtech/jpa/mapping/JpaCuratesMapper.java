package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Curates;
import io.alexdo.mixtech.jpa.entity.CuratesEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaCuratesMapper {
    public abstract CuratesEntity curatesToJpaCurates(Curates curates);
}
