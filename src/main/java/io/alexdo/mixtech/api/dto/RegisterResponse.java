package io.alexdo.mixtech.api.dto;

import lombok.Data;

@Data
public class RegisterResponse {
    private int ret;
    private String msg;
    private Long uid;
    private String uname;

    public RegisterResponse() {}

    public RegisterResponse(int ret, String msg) {
        this.ret = ret;
        this.msg = msg;
    }
}
