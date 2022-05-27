package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CreatesKey.class)
@Table(name = "creates")
@Getter
@Setter
public class CreatesEntity {
    @Id
    private Long uid;
    @Id
    private Long mid;
}
