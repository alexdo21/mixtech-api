package io.alexdo.mixtech.api.infrastructure.security.utils;

public class SystemConstant {

    // token
    public static final int JWT_ERR_EXPIRE = 4001;	//Token expired
    public static final int JWT_ERR_FAIL = 4002;	//authentication failed

    // JWT
    public static final String JWT_SECERT = "jorwdafajiodsjfaiejaoeyt84ythreoiqj";	//secret key
    public static final long JWT_TTL = 60 * 60 * 1000;

    // ret
    public static final int RET_ERR = -1;
    public static final int RET_SUC = 1;

    public static final int RET_ERR_DUPSONG = 5001;
    public static final int RET_ERR_DUPMATCH = 5002;


    // msg
    public static final String MSG_SUCCESS = "success!";
    public static final String MSG_FAIL = "failed!";
    public static final String MSG_USER_DNE = "user not found!";
    public static final String MSG_USER_DUP = "user already existed!";

    public static final String MSG_UNKNOWN_ERR = "unknown error!";

    public static final String MSG_UNAUTH_ACCESS = "Sorry, operation denied!";

    // matches
    public static final String MSG_MATCH_DUPSONG = "duplicate songs in a match!";
    public static final String MSG_MATCH_DUPMATCH = "duplicate match in your library!";
}
