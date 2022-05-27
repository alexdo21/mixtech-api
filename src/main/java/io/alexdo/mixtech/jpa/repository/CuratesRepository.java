package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.CuratesEntity;
import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuratesRepository extends JpaRepository<CuratesEntity, CuratesKey> {
}
