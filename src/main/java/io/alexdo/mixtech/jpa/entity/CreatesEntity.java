package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.CreatesKey;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(CreatesKey.class)
@Table(name = "creates")
@Data
public class CreatesEntity {
    @Id
    private Long uid;
    @Id
    private Long mid;

    public CreatesEntity() {}

    public CreatesEntity(Long uid, Long mid) {
        this.uid = uid;
        this.mid = mid;
    }
}
