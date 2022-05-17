package io.alexdo.mixtech.api.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private int ret;
    private String token;
    private String msg;
    private Long uid;
    private String uname;

    public LoginResponse() {}

    public LoginResponse(int ret, String token, String msg) {
        this.ret = ret;
        this.token = token;
        this.msg = msg;
    }
}
