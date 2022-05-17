package io.alexdo.mixtech.jpa.repository;

import io.alexdo.mixtech.jpa.entity.CreatesEntity;
import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreatesRepository extends JpaRepository<CreatesEntity, CreatesKey> {
}
