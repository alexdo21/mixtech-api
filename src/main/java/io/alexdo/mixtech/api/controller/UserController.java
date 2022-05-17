package io.alexdo.mixtech.api.controller;

import io.alexdo.mixtech.jpa.entity.UserEntity;
import io.alexdo.mixtech.api.dto.LoginResponse;
import io.alexdo.mixtech.api.dto.RegisterResponse;
import io.alexdo.mixtech.application.services.UserService;
import io.alexdo.mixtech.api.infrastructure.security.utils.JwtUtils;
import io.alexdo.mixtech.api.infrastructure.security.utils.SystemConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam String email, @RequestParam String password) {
        LoginResponse loginResponse = new LoginResponse();
        UserEntity userEntity = userService.getByEmail(email);
        if (userEntity == null) {
            loginResponse.setRet(SystemConstant.RET_ERR);
            loginResponse.setMsg(SystemConstant.MSG_USER_DNE);
            return loginResponse;
        }
        String decodedPassord = new String(Base64.getDecoder().decode(userEntity.getPassword()));
        if (decodedPassord.equals(password)) {
            String jwt = JwtUtils.createJWT(userEntity.getUid().toString(),
                    userEntity.getEmail(), SystemConstant.JWT_TTL);
            loginResponse.setRet(SystemConstant.RET_SUC);
            loginResponse.setToken(jwt);
            loginResponse.setMsg(SystemConstant.MSG_SUCCESS);
            loginResponse.setUid(userEntity.getUid());
            loginResponse.setUname(userEntity.getUname());
        } else {
            loginResponse.setRet(SystemConstant.RET_ERR);
            loginResponse.setMsg(SystemConstant.MSG_FAIL);
        }
        return loginResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@RequestBody UserEntity userEntity) {
        RegisterResponse registerResponse = new RegisterResponse();
        if (userService.getByEmail(userEntity.getEmail()) != null) {
            registerResponse.setRet(SystemConstant.RET_ERR);
            registerResponse.setMsg(SystemConstant.MSG_USER_DUP);
        } else if(userEntity.getPassword() == null) {
            registerResponse.setRet(SystemConstant.RET_ERR);
            registerResponse.setMsg("password cannot be empty!");
        } else if (userEntity.getUname() == null) {
            registerResponse.setRet(SystemConstant.RET_ERR);
            registerResponse.setMsg("username cannot be empty!");
        } else {
            userEntity.setPassword(Base64.getEncoder().encodeToString(userEntity.getPassword().getBytes()));
            userService.create(userEntity);
            registerResponse.setRet(SystemConstant.RET_SUC);
            registerResponse.setMsg(SystemConstant.MSG_SUCCESS);
            registerResponse.setUid(userEntity.getUid());
            registerResponse.setUname(userEntity.getUname());
        }
        return registerResponse;
    }
}
