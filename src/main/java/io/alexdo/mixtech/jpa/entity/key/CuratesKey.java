package io.alexdo.mixtech.jpa.entity.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class CuratesKey implements Serializable {
    private Long uid;
    private Long pid;

    public CuratesKey() {}

    public CuratesKey(Long uid, Long pid) {
        this.uid = uid;
        this.pid = pid;
    }
}
