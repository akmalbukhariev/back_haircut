package com.barbie.haircut.api.user.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.FavoriteHairdresserDto;
import com.barbie.haircut.api.dto.UserBookedInfoDto;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.FavoriteHairdresserParam;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserParam;
import com.barbie.haircut.api.param.UserRegParam;

import java.util.List;

public interface IUserService {
    public int login(UserParam param) throws Exception;

    public CamelCaseMap getUser(String phone) throws Exception;

    public int register(UserRegParam param) throws Exception;

    public int updateUserCustomer(UserInfoParam param) throws Exception;

    public int updateUserHairdresser(UserInfoParam param) throws Exception;

    public List<UserBookedInfoDto> getUserBookedList(String userPhone) throws  Exception;

    public int addFavoriteHairdresser(FavoriteHairdresserParam param) throws Exception;

    public List<FavoriteHairdresserDto> getAllFavoriteHairdresser(String userPhone) throws Exception;

    public int updateUserInfo(UserInfoParam param) throws Exception;
}
