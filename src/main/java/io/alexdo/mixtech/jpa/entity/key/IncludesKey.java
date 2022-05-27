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
}
