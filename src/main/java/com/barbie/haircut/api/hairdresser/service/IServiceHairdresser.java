package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.dto.HairdresserInfoDto;
import com.barbie.haircut.api.param.HairdresserParam;
import com.barbie.haircut.api.param.HairdresserResponse;

import java.util.List;

public interface IServiceHairdresser {
    public HairdresserResponse getHairdresserbyNo(int no) throws Exception;
    public List<HairdresserResponse> getHairdresserAll() throws Exception;
    public List<HairdresserInfoDto> getAllHairdresserForUserMainPage() throws Exception;
    public List<HairdresserInfoDto> getHairdresserDetailInfo(String phone) throws Exception;
    public HairdresserParam createNewHairdresser(HairdresserParam hairdresserParam) throws Exception;
    public  int deleteHairdresserbyNo(int no) throws  Exception;
    public HairdresserParam updateHairdresserbyNo(HairdresserParam hairdresserParam, int Id)
            throws Exception;
}
