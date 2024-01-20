package com.barbie.haircut.api.user.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;

public interface IUserService {
    public int login(UserParam param) throws Exception;

    public int register(UserRegistrationParam param) throws Exception;
}
