package com.barbie.haircut.api.user.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegistrationParam;

public interface IUserService {
    public int login(UserParam param) throws Exception;

    public CamelCaseMap getUser(String phone) throws Exception;

    public int register(UserRegistrationParam param) throws Exception;

    public int updateUserCustomer(UserInfoParam param) throws Exception;

    public int updateUserHairdresser(UserInfoParam param) throws Exception;
}
