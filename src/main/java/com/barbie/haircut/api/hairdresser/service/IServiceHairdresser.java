package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.*;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;
import com.barbie.haircut.api.param.UserBookedParam;

import java.util.List;

public interface IServiceHairdresser {

    public CamelCaseMap getHairdresser(String hairdresserPhone) throws  Exception;
    public List<HairdresserInfoDto> getAllHairdresserForUserMainPage() throws Exception;
    public HairdresserInfoDto getHairdresserDetailInfo(String phone) throws Exception;
    public int insertBookedClient(UserBookedParam param) throws  Exception;
}
