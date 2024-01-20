package com.barbie.haircut.api.user.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.UserDto;
import com.barbie.haircut.api.param.UserRegistrationParam;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public CamelCaseMap getUser(String phone) throws Exception;

    public int registerUser(UserRegistrationParam param) throws Exception;
}
