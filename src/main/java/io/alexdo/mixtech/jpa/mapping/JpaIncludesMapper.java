package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Includes;
import io.alexdo.mixtech.jpa.entity.IncludesEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaIncludesMapper {
    public abstract Includes jpaIncludesToIncludes(IncludesEntity includesEntity);
    public abstract IncludesEntity includesToJpaIncludes(Includes includes);
}
