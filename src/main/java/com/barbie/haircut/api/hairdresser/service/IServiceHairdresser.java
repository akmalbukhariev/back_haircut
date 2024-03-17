package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.*;
import com.barbie.haircut.api.param.HairdresserBookedClientHimSelftDto;
import com.barbie.haircut.api.param.HairdresserClientBookParam;
import com.barbie.haircut.api.param.HairdresserRegParam;
import com.barbie.haircut.api.param.UserBookedParam;

import java.util.List;

public interface IServiceHairdresser {

    CamelCaseMap getHairdresser(String hairdresserPhone) throws  Exception;
    List<HairdresserInfoDto> getAllHairdresserForUserMainPage() throws Exception;
    List<HairdresserServiceDto> getHairdresserServiceList(String phone) throws Exception;
    HairdresserInfoDto getHairdresserDetailInfo(String phone) throws Exception;
    int insertBookedClient(UserBookedParam param) throws  Exception;
    int insertHairdresserBookedClient(HairdresserBookedClientHimSelftDto param) throws  Exception;
    int register(HairdresserRegParam param) throws  Exception;
    List<HairdresserBookedClientDto> getBookedClients(HairdresserClientBookParam param) throws Exception;
}
