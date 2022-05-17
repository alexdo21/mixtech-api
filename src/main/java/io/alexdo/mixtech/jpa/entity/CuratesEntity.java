package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.CuratesKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CuratesKey.class)
@Table(name = "curates")
@Data
public class CuratesEntity {
    @Id
    private Long uid;
    @Id
    private Long pid;

    public CuratesEntity() {}

    public CuratesEntity(Long uid, Long pid) {
        this.uid = uid;
        this.pid = pid;
    }
}
