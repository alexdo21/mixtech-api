package io.alexdo.mixtech.jpa.entity.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class FollowsKey implements Serializable {
    private Long pid;
    private Long uid;

    public FollowsKey() {}

    public FollowsKey(Long pid, Long uid) {
        this.pid = pid;
        this.uid = uid;
    }
}
