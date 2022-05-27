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
}
