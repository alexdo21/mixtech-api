package io.alexdo.mixtech.jpa.entity.key;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatesKey implements Serializable {
    private Long uid;
    private Long mid;

    @Override
    public int hashCode() {
        long id = uid + mid;
        return Long.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CreatesKey ck)) {
            return false;
        }
        return ck.uid.equals(uid) && ck.mid.equals(mid);
    }
}
