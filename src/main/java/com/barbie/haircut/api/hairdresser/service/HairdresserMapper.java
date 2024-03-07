package com.barbie.haircut.api.hairdresser.service;

import com.barbie.haircut.api.CamelCaseMap;
import com.barbie.haircut.api.dto.HairdresserDto;
import com.barbie.haircut.api.dto.UserBookedDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HairdresserMapper {
    public CamelCaseMap selectHairdresser(String hairdresserPhone) throws  Exception;
    public List<CamelCaseMap> selectAllHairdresserForUserMainPage() throws  Exception;
    public CamelCaseMap selectHairdresserDetailInfo(String hairdresserPhone) throws  Exception;
    public int insertBookedClient(UserBookedDto dto) throws  Exception;
}
