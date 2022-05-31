package io.alexdo.mixtech.jpa.entity.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuratesKey implements Serializable {
    private Long uid;
    private Long pid;

    @Override
    public int hashCode() {
        long id = uid + pid;
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CuratesKey ck)) {
            return false;
        }
        return ck.uid.equals(uid) && ck.pid.equals(pid);
    }
}
