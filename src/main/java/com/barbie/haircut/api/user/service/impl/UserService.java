package com.barbie.haircut.api.user.service.impl;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.constant.Result;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;
import com.barbie.haircut.api.user.service.IUserService;
import com.barbie.haircut.api.user.service.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

        /*Login with password
        String strPassword = map.get("password").toString();
        if(!psssEncoder.matches(param.getPasswd(), strPassword)){
            return Result.USER_PASSWORD_NOT_MATCHED.getCode();
        }*/
        return Result.SUCCESS.getCode();
    }

    @Override
    public CamelCaseMap getUser(String phone) throws Exception {
        return userMapper.getUser(phone);
    }

    @Override
    public int register(UserRegistrationParam param) throws Exception {

        CamelCaseMap map = userMapper.getUser(param.phone);
        if(map != null && !map.isEmpty()){
            return Result.USER_EXIST.getCode();
        }

        //param.setPassword(psssEncoder.encode(param.getPassword()));
        return userMapper.registerUser(param);
    }

    @Override
    public int updateUserCustomer(UserInfoParam param) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        return userMapper.updateUserCustomer(modelMapper.map(param, UserInfoDto.class));
    }

    @Override
    public int updateUserHairdresser(UserInfoParam param) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        return userMapper.updateUserHairdresser(modelMapper.map(param, UserInfoDto.class));
    }

}
