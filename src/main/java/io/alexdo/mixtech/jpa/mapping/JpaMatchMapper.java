package io.alexdo.mixtech.jpa.mapping;

import io.alexdo.mixtech.application.domain.Match;
import io.alexdo.mixtech.jpa.entity.MatchEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class JpaMatchMapper {
    public abstract MatchEntity matchToJpaMatch(Match match);
    public abstract Match jpaMatchToMatch(MatchEntity matchEntity);
}
