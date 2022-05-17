package io.alexdo.mixtech.api.dto;

import lombok.Data;

@Data
public class StandardResponse {
    private int ret;
    private String msg;

    public StandardResponse() {}

    public StandardResponse(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
