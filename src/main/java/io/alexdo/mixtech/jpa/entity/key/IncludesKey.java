package io.alexdo.mixtech.jpa.entity.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncludesKey implements Serializable {
    private Long pid;
    private String sid;

    @Override
    public int hashCode() {
        return sid.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IncludesKey ik)) {
            return false;
        }
        return ik.pid.equals(pid) && ik.sid.equals(sid);
    }
}
