package com.barbie.haircut.api.user.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.UserDto;
import com.barbie.haircut.api.dto.UserInfoDto;
import com.barbie.haircut.api.param.FavoriteHairdresserParam;
import com.barbie.haircut.api.param.UserInfoParam;
import com.barbie.haircut.api.param.UserRegistrationParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    public CamelCaseMap selectUser(String phone) throws Exception;

    public int insertUser(UserRegistrationParam param) throws Exception;

    public int updateUserCustomer(UserInfoDto dto) throws Exception;

    public int updateUserHairdresser(UserInfoDto dto) throws Exception;

    public List<CamelCaseMap> selectUserBookedList(String userPhone) throws  Exception;

    public int insertFavoriteHairdresser(FavoriteHairdresserParam param) throws Exception;

    public List<CamelCaseMap> selectFavoriteHardresser(String userPhone) throws  Exception;

    public int updateUserInfo(UserInfoParam param) throws Exception;
}
