package com.barbie.haircut.api.user.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;
import com.barbie.haircut.api.user.service.IUserService;
import com.barbie.haircut.api.user.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final PasswordEncoder psssEncoder;

    @Override
    public int login(UserParam param) throws Exception {

        CamelCaseMap map = userMapper.getUser(param.getUserId());
        if(map == null){
            return Result.USER_NOT_EXIST.getCode();
        }

        String strPassword = map.get("password").toString();
        if(!psssEncoder.matches(param.getPasswd(), strPassword)){
            return Result.USER_PASSWORD_NOT_MATCHED.getCode();
        }
        return Result.SUCCESS.getCode();
    }

    @Override
    public int register(UserRegistrationParam param) throws Exception {

        CamelCaseMap map = userMapper.getUser(param.phone);
        if(map != null && !map.isEmpty()){
            return Result.USER_EXIST.getCode();
        }

        param.setPassword(psssEncoder.encode(param.getPassword()));
        return userMapper.registerUser(param);
    }
}
