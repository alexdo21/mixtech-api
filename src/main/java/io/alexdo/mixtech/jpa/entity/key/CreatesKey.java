package io.alexdo.mixtech.jpa.entity.key;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreatesKey implements Serializable {
    private Long uid;
    private Long mid;

    public CreatesKey() {

    }

    public CreatesKey(long uid, Long mid) {
        this.uid = uid;
        this.mid =mid;
    }
}
