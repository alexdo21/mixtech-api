package io.alexdo.mixtech.jpa.entity;

import io.alexdo.mixtech.jpa.entity.key.IncludesKey;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(IncludesKey.class)
@Table(name = "includes")
@Getter
@Setter
public class IncludesEntity {
    @Id
    private Long pid;
    @Id
    private String sid;
}

