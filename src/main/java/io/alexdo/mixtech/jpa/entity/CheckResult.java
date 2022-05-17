package io.alexdo.mixtech.jpa.entity;

import io.jsonwebtoken.Claims;
import lombok.Data;

@Data
public class CheckResult {
    private int errCode;
    private boolean success;
    private Claims claims;
}
