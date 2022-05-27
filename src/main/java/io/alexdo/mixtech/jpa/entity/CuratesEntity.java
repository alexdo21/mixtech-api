package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CuratesKey.class)
@Table(name = "curates")
@Getter
@Setter
public class CuratesEntity {
    @Id
    private Long uid;
    @Id
    private Long pid;
}
