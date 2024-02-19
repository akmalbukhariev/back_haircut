package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.param.hairdresser.HairdresserParam;

import java.util.List;

public interface IServiceHairdresser {
    public HairdresserDto getHairdresserbyNo(int no) throws Exception;
    public List<HairdresserDto> getHairdresserAll() throws Exception;
    public HairdresserParam createNewHairdresser(HairdresserParam hairdresserParam) throws Exception;
    public  int deleteHairdresserbyNo(int no) throws  Exception;
    public HairdresserParam updateHairdresserbyNo(HairdresserParam hairdresserParam, int Id)
            throws Exception;
}
